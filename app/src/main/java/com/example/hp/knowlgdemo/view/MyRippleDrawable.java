package com.example.hp.knowlgdemo.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by HP on 2017/12/24.
 */

public class MyRippleDrawable extends Drawable {

    private int mAlpha = 255;
    private int mRippleColor = 0;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MyRippleDrawable() {
        //抗锯齿
        mPaint.setAntiAlias(true);
        //防抖动
        mPaint.setDither(true);
    }

    public void setmRippleColor(int color) {
        mRippleColor = color;
        onColorOrAlphaChange();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawColor(Color.RED);
    }

    private void onColorOrAlphaChange() {
        mPaint.setColor(mRippleColor);
        //得到颜色透明度
        if (mRippleColor != 255) {
            int alpha = mPaint.getAlpha();
//        Color.alpha(mRippleColor);
            int i = alpha * (mAlpha / 255);
            mPaint.setAlpha(i);
        }
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        //设置Drawable透明度
        mAlpha = alpha;
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        //颜色过滤器
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
