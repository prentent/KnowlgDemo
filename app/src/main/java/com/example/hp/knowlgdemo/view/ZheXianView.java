package com.example.hp.knowlgdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.hp.knowlgdemo.utils.ScreenUtils;

/**
 * 折线图
 */

public class ZheXianView extends View {
    private Context context;
    private int w_size;   //最大宽度
    private int h_size;   //最大高度400dp

    public ZheXianView(Context context) {
        super(context);
        init(context);
    }

    public ZheXianView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ZheXianView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ZheXianView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mPath = new Path();

    private void init(Context context) {
        this.context = context;
        Paint paint = mPaint;
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.parseColor("#0085ee"));
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
            h_size = (int) (400 * getResources().getDisplayMetrics().density);
        }
        setMeasuredDimension(w_size, h_size);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawXTitle(canvas);
        drawXLine(canvas);
        drawYLine(canvas);
        drawXPoint(canvas);
        drawYPoint(canvas);
        drawZX(canvas);
    }

    private void drawZX(Canvas canvas) {
        Paint paint = mPaint;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        Shader shader = new LinearGradient(0, 0, 0, h_size - 150, new int[]{
                0xffff0000,
                0xffff7f00,
                0xff0000ff,
                0xff00ffff,
                0xffff0077
        }
                , null, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        int height = h_size - 250;
        int height_ = height / 5;
        int width = w_size - 200;
        int width_ = width / 8;
        Path path = mPath;
        path.moveTo(100, h_size - 150);
        int[] array = {4, 3, 1, 2, 4, 2, 1, 3};
        for (int i = 0; i < 8; i++) {
            path.lineTo(100 + width_ * i, h_size - height_ * array[i] - 150);
        }
        path.lineTo(100 + width_ * 7, h_size - 150);
        canvas.drawPath(path, paint);
        path.reset();
        path.close();
    }

    //分成5份,测试
    private void drawYPoint(Canvas canvas) {
        Paint paint = mPaint;
        paint.setColor(Color.RED);
        int height = h_size - 250;
        int height_ = height / 5;
        for (int i = 0; i < 5; i++) {
            canvas.drawText(i + "", 50, h_size - 150 - height_ * i, paint);
        }
    }

    //分成8份,测试
    private void drawXPoint(Canvas canvas) {
        Paint paint = mPaint;
        paint.setColor(Color.RED);
        int width = w_size - 200;
        int width_ = width / 8;
        for (int i = 0; i < 8; i++) {
            canvas.drawText(i + "月", 100 + width_ * i, h_size - 100, paint);
        }
    }

    private void drawYLine(Canvas canvas) {
        Paint paint = mPaint;
        paint.setTextSize(10 * getResources().getDisplayMetrics().density);
        canvas.drawLine(100, 100, 100, h_size - 150, paint);
        canvas.drawLine(90, 110, 100, 100, paint);
        canvas.drawLine(110, 110, 100, 100, paint);
        canvas.drawText("y", 100, 50, paint);
    }

    private void drawXLine(Canvas canvas) {
        Paint paint = mPaint;
        paint.setTextSize(10 * getResources().getDisplayMetrics().density);
        canvas.drawLine(100, h_size - 150, w_size - 100, h_size - 150, paint);
        canvas.drawLine(w_size - 110, h_size - 140, w_size - 100, h_size - 150, paint);
        canvas.drawLine(w_size - 110, h_size - 160, w_size - 100, h_size - 150, paint);
        canvas.drawText("x", w_size - 50, h_size - 150, paint);
    }

    private void drawXTitle(Canvas canvas) {
        Paint paint = mPaint;
        paint.setTextSize(20 * getResources().getDisplayMetrics().density);
        canvas.drawText("折线图", w_size / 2 - mPaint.measureText("折线图") / 2, h_size - 20, paint);
    }

}
