package com.example.hp.knowlgdemo.ui;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image6);
        img1.setImageBitmap(bitmap);
        img2.setImageBitmap(BitmapUtils.handleImageNegative(bitmap));
        img3.setImageBitmap(BitmapUtils.handleImagePixelsOldPhoto(bitmap));
        img4.setImageBitmap(BitmapUtils.handleImagePixelsRelife(bitmap));

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Bitmap bitmap1 = BitmapUtils.handleImageNegative(bitmap);
//                Message message = new Message();
//                message.obj = bitmap1;
//                message.what = 0;
//                handler.sendMessage(message);
//            }
//        }).start();


    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("======", "sssss");
            Bitmap obj = (Bitmap) msg.obj;
            img1.setImageBitmap(obj);

        }
    };
}
