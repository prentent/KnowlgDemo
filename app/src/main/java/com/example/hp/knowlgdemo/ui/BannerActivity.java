package com.example.hp.knowlgdemo.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.knowlgdemo.R;
import com.example.hp.knowlgdemo.adapter.BannerAdapter;
import com.example.hp.knowlgdemo.utils.AlphaPageTransformer;
import com.example.hp.knowlgdemo.view.MyBannerView;

public class BannerActivity extends AppCompatActivity {
    private int[] imgRes = {
            R.mipmap.image1, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4,
            R.mipmap.image5, R.mipmap.image6, R.mipmap.image7, R.mipmap.image8
    };
    private MyBannerView vpr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        initView();
        initAdapter();
    }

    /*
    注册adapter
     */
    private void initAdapter() {
//        vpr.setAdapter(new BannerAdapter(this, imgRes));
    }

    /*
    注册view
     */
    private void initView() {
        vpr = (MyBannerView) findViewById(R.id.vpr);
        vpr.startBanner();
//        //设置page间的间隔
//        vpr.setPageMargin(30);
//        //设置缓存的页面数
//        vpr.setOffscreenPageLimit(3);
//
//        vpr.setPageTransformer(true, new AlphaPageTransformer());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vpr.stopBanner();
    }
}
