package com.example.hp.knowlgdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by HP on 2018/1/7.
 */

public class TouchPullView2 extends View {
    private int mDargHeight = 400;  //最大可下拉的高度
    private float mCirclePointX, mCirclePointY; //圆心坐标
    private float mCircleRadius = 50;
    private float progress = 0.0f;
    private int widthMeasure;
    private int heightMeasure;
    private Interpolator mTanentAngleInterpolator;
    private int mTargetGravityHeight = 10; //重心点最终高度，决定控制点的Y坐标
    private int mTargetAngle = 105; //角度变换 0~135
    private Interpolator mProgessInterpolator = new DecelerateInterpolator(); //一个由快到慢的插值器

    public TouchPullView2(Context context) {
        super(context);
        init();
    }

    public TouchPullView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchPullView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TouchPullView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mPath = new Path();

    private void init() {
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        //初始化路径插值器
        mTanentAngleInterpolator = PathInterpolatorCompat.create((mCircleRadius * 2) / mDargHeight, 90.0f / mTargetAngle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int MIN_W = (int) (mCircleRadius * 2 + getPaddingLeft() + getPaddingRight()); //需要的最小宽度
        int MIN_H = (int) ((mDargHeight * progress + 0.5f)  //mDargHeight * mProgress = moveSize(即actionMove.getY - actionDown.getY),+0.5f为四舍五入
                + getPaddingBottom() + getPaddingTop());
        widthMeasure = getMeasureSize(widthMeasureSpec, MIN_W);
        heightMeasure = getMeasureSize(heightMeasureSpec, MIN_H);
        setMeasuredDimension(widthMeasure, heightMeasure);
    }

    /**
     * 获取所需要的宽/高的测量结果
     *
     * @param Spec     测量模式
     * @param minValue 规定的最小值
     * @return 测量结果
     */
    private int getMeasureSize(int Spec, int minValue) {
        int result;
        int mode = MeasureSpec.getMode(Spec);
        int size = MeasureSpec.getSize(Spec);
        switch (mode) {
            case MeasureSpec.AT_MOST: //wrap_content
                result = Math.min(size, minValue); //取测量值和规定的最小宽度中的最小值
                break;
            case MeasureSpec.EXACTLY: //match_parent or exactly num
                result = size;
                break;
            default: //其余情况取最小值
                result = minValue;
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制贝塞尔
        canvas.drawPath(mPath, mPaint);
        //画圆
        canvas.drawCircle(mCirclePointX, mCirclePointY, mCircleRadius, mPaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        undeteLayout();
    }

    public void setProgress(float progress) {
        this.progress = progress;
        requestLayout();
    }

    private void undeteLayout() {
        float interpolation = mProgessInterpolator.getInterpolation(progress);
        //圆
        mCirclePointX = widthMeasure / 2;
        mCirclePointY = getValueByLine(0, mDargHeight, progress) - mCircleRadius;
        //控制点结束Y的值
        final float endPointY = mTargetGravityHeight;

        final Path path = mPath;
        path.reset(); //重置
        path.moveTo(0, 0);

        //坐标系是以最左边的起始点为原点
        float lEndPointX, lEndPointY; //结束点的X,Y坐标
        float lControlPointX, lControlPointY; //控制点的X，Y坐标
        //获取当前切线的弧度
        double angle = mTanentAngleInterpolator.getInterpolation(interpolation) * mTargetAngle;//获取当前的角度
        double radian = Math.toRadians(angle); //获取当前弧度
        float x = (float) (Math.sin(radian) * mCircleRadius);  //求出“股”的长度（长的那条直角边）
        float y = (float) (Math.cos(radian) * mCircleRadius);  //求出“勾”的长度（短的那条直角边）
        lEndPointX = mCirclePointX - x; //以起始点为原点，x坐标就等于圆的X坐标减去股的长度
        lEndPointY = mCirclePointY + y; //以起始点为原点，y坐标就等于圆的y坐标加上勾的长度
        lControlPointY = getValueByLine(0, endPointY, interpolation);//获取控制点的Y坐标
        float tHeight = lEndPointY - lControlPointY; //结束点与控制点的Y坐标差值
        float tWidth = (float) (tHeight / Math.tan(radian));  //通过计算两个角度是相等的，因此弧度依旧适用
        lControlPointX = lEndPointX - tWidth; //结束点的x - ‘勾’ 的长度求出了控制点的X坐标

        path.quadTo(lControlPointX, lControlPointY, lEndPointX, lEndPointY); //画左边贝塞尔曲线
        path.lineTo(mCirclePointX + (mCirclePointX - lEndPointX), lEndPointY); //左右两个结束点相连
        path.quadTo(mCirclePointX + (mCirclePointX - lControlPointX), lControlPointY, widthMeasure, 0); //画右边贝塞尔曲线
    }

    private float getValueByLine(float star, float end, float mProgress) {
        return star + (end - star) * mProgress;
    }

    private ValueAnimator valueAnimator;

    public void release() {
        if (valueAnimator == null) {
            ValueAnimator animator = ValueAnimator.ofFloat(progress, 0);
            animator.setInterpolator(new DecelerateInterpolator());
            animator.setDuration(400);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Object val = animation.getAnimatedValue();
                    if (val instanceof Float) {
                        setProgress((Float) val);
                    }
                }
            });
            valueAnimator = animator;
        } else {
            valueAnimator.cancel();
            valueAnimator.setFloatValues(progress, 0);
        }
        valueAnimator.start();
    }

}
