package com.example.hp.knowlgdemo.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.hp.knowlgdemo.utils.ScreenUtils;

/**
 * 饼状图
 */

public class BingZhuangView extends View {

    private Context context;
    private int w_size;   //最大宽度
    private int h_size;   //最大高度400dp
    private float animatedValue;

    public BingZhuangView(Context context) {
//        super(context);
        this(context, null);
    }

    public BingZhuangView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs, 0);
    }

    public BingZhuangView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BingZhuangView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
        this(context, attrs, defStyleAttr);
    }


    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.context = context;
        Paint paint = mPaint;
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w_mode = MeasureSpec.getMode(widthMeasureSpec);
        int h_mode = MeasureSpec.getMode(heightMeasureSpec);
        w_size = MeasureSpec.getSize(widthMeasureSpec);
        h_size = MeasureSpec.getSize(heightMeasureSpec);
        if (w_mode == MeasureSpec.AT_MOST) {
            w_size = ScreenUtils.getScreenWidth(context);
        }
        if (h_mode == MeasureSpec.AT_MOST) {
            h_size = ScreenUtils.getScreenHeight(context);
        }
        setMeasuredDimension(w_size, h_size);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //数据源
    private int[][] arrays = {{5, Color.BLUE}, {3, Color.CYAN}, {2, Color.RED}, {5, Color.GREEN},
            {4, Color.LTGRAY}, {3, Color.MAGENTA}, {2, Color.YELLOW},};

    @Override
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = mPaint;
        //24
        //设置圆的半径
        float mRadius = 0.0f;
        if (w_size > h_size) {
            mRadius = h_size / 2 - 200;
        } else {
            mRadius = w_size / 2 - 200;
        }
        float startAngle = 0.0f;
        float sweepAngle = 0.0f;
        int calibration = 360 / 24;

        //圆心
        float mXPoint = w_size / 2;
        float mYPoint = h_size / 2;
        RectF rectF = new RectF(mXPoint - mRadius, mYPoint - mRadius, mXPoint + mRadius, mYPoint + mRadius);

        for (int i = 0; i < arrays.length; i++) {
            sweepAngle = arrays[i][0] * calibration;
            paint.setColor(arrays[i][1]);
            canvas.drawArc(rectF, startAngle, sweepAngle - 1, true, paint);
            startAngle = sweepAngle + startAngle;
        }
//        paint.setColor(Color.WHITE);
//        canvas.drawCircle(mXPoint, mYPoint, mRadius / 2, paint);
//        initAnimator();
    }

    private void initAnimator() {
        ValueAnimator anim = ValueAnimator.ofFloat(0, 360);
        anim.setDuration(10000);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animatedValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        anim.start();
    }
}
