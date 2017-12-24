package com.example.hp.knowlgdemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;

/**
 * 一个图片加载的工具类
 */

public class ImageLoader {

    private static final String TAG = "==ImageLoader";

    private ImageLoader() {
    }

    private static ImageLoader imageLoader;

    public static ImageLoader getImageLoader() {
        if (imageLoader == null)
            imageLoader = new ImageLoader();
        return imageLoader;
    }

    public void disPlay(String str, OnFetchBitmapListener listener) {
        //t推按地址为空
        if (TextUtils.isEmpty(str)) {
            return;
        }
        //接口没有实力化的时候
        if (listener == null) {
            return;
        }

        OnFetchBitmapListener bitmapListener = listener;

        //判断内存缓存是否有
        Bitmap lruBitmap = LruCacheUtils.getCacheUtils().getBitmap(str);
        if (lruBitmap != null) {
            listener.onSucess(lruBitmap);
            LogUtils.i(TAG, "===LruCacheUtils");
            return;
        }

        new MyAsck(bitmapListener).execute(str);

    }

    private class MyAsck extends AsyncTask<String, Void, Bitmap> {
        OnFetchBitmapListener listener;

        public MyAsck(OnFetchBitmapListener listener) {
            this.listener = listener;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //获取图片地址
            String url = params[0];
            byte[] bytes = DiskCacheUtils.getDiskCacheUtils().get(url);
            //磁盘缓存有的话则返回磁盘缓存的即可
            if (bytes != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                LogUtils.i(TAG, "===DiskCacheUtils");
                return bitmap;
            }
            byte[] data = HttpUtils.download(url);
            if (data != null) {
                //保存到磁盘
                DiskCacheUtils.getDiskCacheUtils().put(url, data);
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                //保存到内存缓存
                LruCacheUtils.getCacheUtils().putBitmap(url, bitmap);
                LogUtils.i(TAG, "===HttpUtils");
                return bitmap;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute(bitmap);
            if (bitmap == null) {
                listener.onFail();
                return;
            }
            listener.onSucess(bitmap);
        }
    }


    public interface OnFetchBitmapListener {
        void onSucess(Bitmap bitmap);

        void onFail();
    }

}
