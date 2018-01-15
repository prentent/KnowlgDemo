package com.example.hp.knowlgdemo.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.hp.knowlgdemo.R;

/**
 * Created by HP on 2018/1/11.
 */

public class MatrixImageView extends View {

    private Matrix matrix;
    private Bitmap bitmap;

    public MatrixImageView(Context context) {
        super(context);
        init();
    }

    public MatrixImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MatrixImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint paint;
    private void init() {
        paint=new Paint();
        matrix = new Matrix();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }

    public void setImageMatric(float[] floats) {
        matrix.setValues(floats);
    }

    //bitmapShader效果
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawBitmap(bitmap, matrix, null);
         BitmapShader shader=new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
        paint.setShader(shader);
        canvas.drawCircle(50,50,200,paint);
    }
}
