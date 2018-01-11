package com.example.hp.knowlgdemo.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Bitmap工具类
 */

public class BitmapUtils {
	
	/** 
 * 质量压缩方法 
 * 
 * @param image 
 * @return 
 */  
public static Bitmap compressImage(Bitmap image) {  
  
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中  
    int options = 90;  
  
    while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩  
        baos.reset(); // 重置baos即清空baos  
        image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中  
        options -= 10;// 每次都减少10  
    }  
    ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
    Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片  
    return bitmap;  
}  
	
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

    /**
     * 色光三原色处理
     * 这里设置的rgb三个值都是一个，顾没有ps那么丰富(RGBA)
     *
     * @param bitmap
     * @param hue        0-255     色调    (progreee-mid)*1.0f/mid*180
     * @param stauration 0-255   饱和度    progreee*1.0f/mid
     * @param lum        0-255     亮度    progreee*1.0f/mid
     * @return
     */
    public static Bitmap handleImageEffect(Bitmap bitmap, float hue, float stauration, float lum) {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        ColorMatrix matrix = new ColorMatrix();
        matrix.setRotate(0, hue);//R
        matrix.setRotate(1, hue);//G
        matrix.setRotate(2, hue);//B

        ColorMatrix matrix1 = new ColorMatrix();
        matrix1.setSaturation(stauration);  //饱和度

        ColorMatrix matrix2 = new ColorMatrix();
        matrix2.setScale(lum, lum, lum, 1);//亮度

        ColorMatrix matrix3 = new ColorMatrix();
        matrix3.postConcat(matrix);
        matrix3.postConcat(matrix1);
        matrix3.postConcat(matrix2);

        paint.setColorFilter(new ColorMatrixColorFilter(matrix3));

        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bmp;
    }

    /**
     * 色光三原色处理
     * 使用矩阵的方式(RGBA)
     *
     * @param bitmap
     * @param floats [20]    矩阵数组
     * @return
     */
    public static Bitmap handleImageEffect(Bitmap bitmap, float[] floats) {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);

        ColorMatrix matrix = new ColorMatrix();
        matrix.set(floats);
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));

        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bmp;
    }

    /**
     * 图像底片效果算法
     *
     * @param bm
     * @return
     */
    public static Bitmap handleImageNegative(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color = 0;
        int r, g, b, a;
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];

        //获取原像素大小
        // （1）起始偏移量，（2）行距，（3,4）第一个x ,y的坐标，（5,6）长度
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);
        for (int i = 0; i < width * height; i++) {
            color = oldPx[i];
            //读取原图片像素
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            //底片效果算法
            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }
            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }
            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }

            //重新复制给像素点
            newPx[i] = Color.argb(a, r, g, b);
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }

    /**
     * 图像怀旧效果算法
     *
     * @param bm
     * @return
     */
    public static Bitmap handleImagePixelsOldPhoto(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color = 0;
        int r, g, b, a;
        int r1, g1, b1;
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];

        //获取原像素大小
        // （1）起始偏移量，（2）行距，（3,4）第一个x ,y的坐标，（5,6）长度
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);
        for (int i = 0; i < width * height; i++) {
            color = oldPx[i];
            //读取原图片像素
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            //怀旧效果算法
            r1 = (int) (0.393 * r + 0.769 * g + 0.189 * b);
            g1 = (int) (0.349 * r + 0.686 * g + 0.168 * b);
            b1 = (int) (0.272 * r + 0.534 * g + 0.131 * b);

            if (r1 > 255) {
                r1 = 255;
            }
            if (g1 > 255) {
                g1 = 255;
            }
            if (b1 > 255) {
                b1 = 255;
            }

            //重新复制给像素点
            newPx[i] = Color.argb(a, r1, g1, b1);
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }

    /**
     * 图像浮雕效果算法
     *
     * @param bm
     * @return
     */
    public static Bitmap handleImagePixelsRelife(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color = 0, colorBefore = 0;
        int r, g, b, a;
        int r1, g1, b1;
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];

        //获取原像素大小
        // （1）起始偏移量，（2）行距，（3,4）第一个x ,y的坐标，（5,6）长度
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);
        for (int i = 1; i < width * height; i++) {
            colorBefore = oldPx[i - 1];
            //读取原图片像素
            r = Color.red(colorBefore);
            g = Color.green(colorBefore);
            b = Color.blue(colorBefore);
            a = Color.alpha(colorBefore);

            color = oldPx[i];
            r1 = Color.red(color);
            g1 = Color.green(color);
            b1 = Color.blue(color);

            //浮雕效果算法
            r = r - r1 + 127;
            g = g - g1 + 127;
            b = b - b1 + 127;

            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }

            //重新复制给像素点
            newPx[i] = Color.argb(a, r, g, b);
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }
}
