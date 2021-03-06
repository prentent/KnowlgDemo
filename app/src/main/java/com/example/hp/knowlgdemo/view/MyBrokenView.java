package com.example.hp.knowlgdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.hp.knowlgdemo.utils.ScreenUtils;

/**
 * Created by HP on 2018/1/7.
 */

public class MyBrokenView extends View {
    private Context context;
    private int w_size;
    private int h_size;

    public MyBrokenView(Context context) {
        this(context, null);
//        super(context);
    }

    public MyBrokenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
//        super(context, attrs);
    }

    public MyBrokenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyBrokenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    //    private final Path mPath = new Path();
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.context = context;
        Paint paint = mPaint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setDither(true);
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
            h_size = 500;
        }
        setMeasuredDimension(w_size, h_size);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTitle(canvas);
        drawXLine(canvas);
        drawYLine(canvas);
        drawXPoint(canvas);
        drawYPoint(canvas);
        drawBroken(canvas);
    }

    private void drawBroken(Canvas canvas) {
        int[][] t_Res = {{2, Color.BLUE}, {4, Color.DKGRAY}, {1, Color.GRAY},
                {3, Color.GREEN}, {4, Color.LTGRAY}, {1, Color.RED},
                {2, Color.MAGENTA}, {4, Color.YELLOW}};
        int width = (w_size - 200) / 8;
        int height = (h_size - 250) / 5;
        Paint paint = mPaint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.BLUE);
//        Path path1 = new Path();
//        path1.moveTo(100, h_size - 150);
//        path1.lineTo(width * 8, h_size - 150 - h_size - 150 - height * t_Res[0][0]);
//        path1.close();
//        canvas.drawPath(path1, paint);
        for (int i = 1; i < 8; i++) {
            //paint.setColor(t_Res[i - 1][1]);

//            Path path = new Path();
//            path.lineTo(width * (i) + 100, h_size - 150 - height * t_Res[i][0]);
//            path.lineTo(width * i + 100, h_size - 150);
//            path.close();
//            canvas.drawPath(path, paint);
//            canvas.drawCircle(width * (i) + 100, h_size - 150 - height * t_Res[i][0], 5, paint);
            canvas.drawLine(width * (i - 1) + 100, h_size - 150 - height * t_Res[i - 1][0],
                    width * (i) + 100, h_size - 150 - height * t_Res[i][0], paint);
        }
    }

    private void drawYPoint(Canvas canvas) {
        int height = (h_size - 250) / 5;
        Paint paint = mPaint;
        paint.setTextSize(10 * getResources().getDisplayMetrics().density);
        for (int i = 0; i < 5; i++) {
            canvas.drawText(i + "", 50, h_size - 150 - height * i, paint);
        }
    }

    private void drawXPoint(Canvas canvas) {
        int width = (w_size - 200) / 8;
        Paint paint = mPaint;
        paint.setColor(Color.RED);
        paint.setTextSize(10 * getResources().getDisplayMetrics().density);
        for (int i = 0; i < 8; i++) {
            canvas.drawText(i + "月", 100 + width * i, h_size - 100, paint);
        }
    }

    private void drawYLine(Canvas canvas) {
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(100, 100, 100, h_size - 150, mPaint);
        canvas.drawLine(90, 110, 100, 100, mPaint);
        canvas.drawLine(110, 110, 100, 100, mPaint);
    }

    private void drawXLine(Canvas canvas) {
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(100, h_size - 150, w_size - 50, h_size - 150, mPaint);
        canvas.drawLine(w_size - 60, h_size - 140, w_size - 50, h_size - 150, mPaint);
        canvas.drawLine(w_size - 60, h_size - 160, w_size - 50, h_size - 150, mPaint);
    }

    private void drawTitle(Canvas canvas) {
        mPaint.setTextSize(20 * getResources().getDisplayMetrics().density);
        mPaint.setColor(Color.BLUE);
        canvas.drawText("折线图", w_size / 2 - mPaint.measureText("直方图") / 2, h_size - 20, mPaint);
    }

}
