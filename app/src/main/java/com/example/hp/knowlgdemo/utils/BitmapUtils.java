package com.example.hp.knowlgdemo.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.io.InputStream;

/**
 * Bitmap工具类
 */

public class BitmapUtils {
    /**
     * 文件路劲加载
     *
     * @param filePath 图片路劲
     * @param pixelW   正真的宽
     * @param piexlH   正真的高
     * @return
     */
    public static Bitmap ratioFile(String filePath, int pixelW, int piexlH, Bitmap.Config rgb) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = rgb;
        //预加载
        BitmapFactory.decodeFile(filePath, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        //采样
        options.inSampleSize = getSimleSize(outWidth, outHeight, pixelW, piexlH);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * ByteArray数组加载
     *
     * @param data   bute数组
     * @param pixelW 真实宽
     * @param piexlH 真实高
     * @return
     */
    public static Bitmap ratioByteArray(byte[] data, int pixelW, int piexlH, Bitmap.Config rgb) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = rgb;
        //预加载
        BitmapFactory.decodeByteArray(data, 0, data.length);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        //采样
        options.inSampleSize = getSimleSize(outWidth, outHeight, pixelW, piexlH);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }

    /**
     * 流加载
     *
     * @param is     流
     * @param pixelW 真实宽
     * @param piexlH 真实高
     * @return
     */
    public static Bitmap ratioStream(InputStream is, int pixelW, int piexlH, Bitmap.Config rgb) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = rgb;
        //预加载
        BitmapFactory.decodeStream(is);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        //采样
        options.inSampleSize = getSimleSize(outWidth, outHeight, pixelW, piexlH);
        options.inJustDecodeBounds = false;
        //封装
        Rect rectF = new Rect(0, 0, pixelW, piexlH);
        return BitmapFactory.decodeStream(is, rectF, options);
    }

    /**
     * 资源加载
     *
     * @param res    资源
     * @param id     资源对象
     * @param pixelW 真实宽
     * @param piexlH 真实高
     * @return
     */
    public static Bitmap ratioResource(Resources res, int id, int pixelW, int piexlH, Bitmap.Config rgb) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = rgb;
        //预加载
        BitmapFactory.decodeResource(res, id);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        //采样
        options.inSampleSize = getSimleSize(outWidth, outHeight, pixelW, piexlH);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, id, options);
    }

    /**
     * 采样
     *
     * @param outWidth  原始宽
     * @param outHeight 原始高
     * @param pixelW    真实宽
     * @param piexlH    真实高
     * @return
     */
    private static int getSimleSize(int outWidth, int outHeight, int pixelW, int piexlH) {

        int simpleSiza = 1;
        if (outWidth > outHeight && outWidth > pixelW) {
            simpleSiza = outWidth / pixelW;
        } else if (outWidth < outHeight && outHeight > piexlH) {
            simpleSiza = outHeight / piexlH;
        }
        if (simpleSiza <= 0) {
            simpleSiza = 1;
        }
        return simpleSiza;
    }
}
