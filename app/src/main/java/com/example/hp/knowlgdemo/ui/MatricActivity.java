package com.example.hp.knowlgdemo.ui;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hp.knowlgdemo.R;

public class MatricActivity extends AppCompatActivity {

    private MatrixImageView img;
    private ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn_tx_m);
        img = (MatrixImageView) findViewById(R.id.img111);
        img1 = (ImageView) findViewById(R.id.img1);

    }

    //矩阵变化
    float[] ints = {1, 0, 200, 0, 1, 100, 0, 0, 1};

    public void change(View view) {
//        Toast.makeText(this,"xxx",Toast.LENGTH_SHORT).show();
//        img.setImageMatric(ints);
//        img.invalidate();
        img1.setScaleType(ImageView.ScaleType.MATRIX);
//
        Matrix matrix=new Matrix();

//        matrix.setValues(ints);
//        matrix.setValues(ints);
        matrix.setTranslate(100,100);
        matrix.postScale(2,2);
        matrix.postRotate(10);

        img1.setImageMatrix(matrix);
//        img1.setTranslationX(100);
         img1.invalidate();
    }
}
