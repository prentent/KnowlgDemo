package com.example.hp.knowlgdemo.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hp.knowlgdemo.R;
import com.example.hp.knowlgdemo.utils.BitmapUtils;
import com.example.hp.knowlgdemo.utils.HttpUtils;
import com.example.hp.knowlgdemo.utils.ImageLoader;

public class BitmapActivity extends AppCompatActivity {

    private ImageView img;
    private static final String PATH = "http://p0.so.qhimgs1.com/bdr/_240_/t01f555fd21a3e87694.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        initView();
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image1);
//        Log.e("=======bitmap", bitmap.getByteCount() + "");
//        Bitmap bm = BitmapUtils.ratioResource(getResources(), R.mipmap.image1, img.getWidth(), img.getHeight());
//        Log.e("=======bm", bm.getByteCount() + "");
//        img.setImageBitmap(bm);
        //  new MyAsyncTask().execute(PATH);
        ImageLoader.getImageLoader().disPlay(PATH, new ImageLoader.OnFetchBitmapListener() {
            @Override
            public void onSucess(Bitmap bitmap) {
                img.setImageBitmap(bitmap);
            }

            @Override
            public void onFail() {
                Toast.makeText(BitmapActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private class MyAsyncTask extends AsyncTask<String, Void, byte[]> {

        @Override
        protected byte[] doInBackground(String... params) {
            byte[] ret = null;

            //获取到网址
            String path = params[0];
            //下载数据
            ret = HttpUtils.download(path);
            //返回过去
            return ret;
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
//            super.onPostExecute(bytes);
            if (bytes != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(
                        bytes, 0, bytes.length);
                Log.e("=======bitmap", bitmap.getByteCount() + "");
                Bitmap bm = BitmapUtils.ratioByteArray(bytes, img.getWidth(), img.getHeight(), Bitmap.Config.RGB_565);
                Log.e("=======bm", bm.getByteCount() + "");
                img.setImageBitmap(bm);
            }
        }
    }

    /*
    biemap加载方式
     */
    private void decoedBitmap() {
//        BitmapFactory.decodeByteArray();
//        BitmapFactory.decodeFile();
//        BitmapFactory.decodeResource();
//        BitmapFactory.decodeStream();
    }
}
