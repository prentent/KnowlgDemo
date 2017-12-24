package com.example.hp.knowlgdemo.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 一个bitmap内存缓存工具类
 */

public class LruCacheUtils {

    private LruCache<String, Bitmap> bitmapLruCache;
    private static LruCacheUtils cacheUtils;
    private static final String TAG = "==LruCacheUtils";

    public static LruCacheUtils getCacheUtils() {
        if (cacheUtils == null)
            cacheUtils = new LruCacheUtils();
        return cacheUtils;
    }


    /*
    初始化
     */
    private LruCacheUtils() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        LogUtils.i(TAG, maxMemory + "");
        final int max = (int) (maxMemory / 8);
        bitmapLruCache = new LruCache<String, Bitmap>(max) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    /*
    添加bitmap到缓存
     */
    public void putBitmap(String str, Bitmap bitmap) {
        if (bitmap == null)
            throw new NullPointerException("Bitmap不能为空");
        LogUtils.i(TAG, "写入内存成功");
        bitmapLruCache.put(str, bitmap);
    }

    /*
    获取bitmap
     */
    public Bitmap getBitmap(String str) {
        Bitmap bitmap = bitmapLruCache.get(str);
        if (bitmap != null)
            return bitmap;
        return null;
    }

}
