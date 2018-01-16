package com.example.hp.knowlgdemo.view;

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
import android.view.Gravity;
import android.view.View;

import com.example.hp.knowlgdemo.utils.ScreenUtils;

/**
 * 精致的打钩动画
 */

public class TickView extends View {

    private int xPoint;
    private int xRadius;
    private Context context;
    private int currentAngle = 0;
    private int pointRadius;
    private int paintWidth = 0;
    private boolean isScale = false;

    public TickView(Context context) {
//        super(context);
        this(context, null);
    }

    public TickView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs, 0);
    }

    public TickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
        this(context, attrs, defStyleAttr);
    }

    private Paint mPaint;
    private RectF rectF;

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.context = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w_size = MeasureSpec.getSize(widthMeasureSpec);
        int h_size = MeasureSpec.getSize(heightMeasureSpec);
        int h_mode = MeasureSpec.getMode(heightMeasureSpec);
        int w_mode = MeasureSpec.getMode(widthMeasureSpec);
        if (h_mode == MeasureSpec.AT_MOST) {
            h_size = ScreenUtils.getScreenHeight(context);
        }
        if (w_mode == MeasureSpec.AT_MOST) {
            w_size = ScreenUtils.getScreenWidth(context);
        }

        if (h_size > w_size) {
            h_size = w_size;
        } else if (w_size > h_size) {
            w_size = h_size;
        }

        xPoint = w_size / 2;
        xRadius = 100;
        pointRadius = 100;
        rectF = new RectF(xPoint - 100, xPoint - 100, xPoint + 100, xPoint + 100);

        setMeasuredDimension(w_size, h_size);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rect = rectF;
        Paint paint = mPaint;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.LTGRAY);
        canvas.drawCircle(xPoint, xPoint, xRadius, paint);
        //画圆弧
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        if (currentAngle <= 360) {
            canvas.drawArc(rect, 0, currentAngle, false, paint);

            if (currentAngle > 360) {
                currentAngle = 360;
            } else {
                currentAngle += 10;
            }
        }
        paint.setStyle(Paint.Style.FILL);
        if (pointRadius >= 0 && currentAngle > 360) {

            paint.setColor(Color.YELLOW);
            canvas.drawCircle(xPoint, xPoint, xRadius, paint);
            if (pointRadius == 0) {
                pointRadius = 5;
            } else {
                pointRadius -= 5;
            }
            paint.setColor(Color.WHITE);
            canvas.drawCircle(xPoint, xPoint, pointRadius, paint);
        }
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        if (paintWidth >= 0 && pointRadius == 0 && currentAngle > 360) {
            if (paintWidth <= 45 && !isScale) {
                paintWidth += 1;
                paint.setStrokeWidth(paintWidth);
                if (paintWidth == 45) {
                    isScale = true;
                }
            } else if (isScale) {
                paintWidth -= 1;
                paint.setStrokeWidth(paintWidth);
                if (paintWidth == 0) {
                    isScale = false;
                    paintWidth -= 5;
                }
            }
            canvas.drawArc(rect, 0, 360, false, paint);
        }
        if (paintWidth >= 0) {
            postInvalidate();
        }
    }
}
