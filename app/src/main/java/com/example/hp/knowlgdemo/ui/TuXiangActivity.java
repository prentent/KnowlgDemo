package com.example.hp.knowlgdemo.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.hp.knowlgdemo.R;
import com.example.hp.knowlgdemo.utils.BitmapUtils;

/**
 * Created by Administrator on 2018/1/10.
 */

public class TuXiangActivity extends AppCompatActivity {

    private ImageView img2;
    private ImageView img1;
    private ImageView img3;
    private ImageView img4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_xiang);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image6);
        img1.setImageBitmap(bitmap);
        img2.setImageBitmap(BitmapUtils.handleImageNegative(bitmap));
        img3.setImageBitmap(BitmapUtils.handleImagePixelsOldPhoto(bitmap));
        img4.setImageBitmap(BitmapUtils.handleImagePixelsRelife(bitmap));

    }

}
