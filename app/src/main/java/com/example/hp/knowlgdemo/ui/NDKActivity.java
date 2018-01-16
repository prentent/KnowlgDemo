package com.example.hp.knowlgdemo.ui;

import android.content.AsyncQueryHandler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * NDK测试
 * <p>
 * jni头文件生成
 * javah -d jni -classpath D:\Android\sdk\extras\android\m2repository\com\android\support\appcompat-v7\23.2.1\appcompat-v7-23.2.1-sources.jar;
 * ..\..\build/intermediates/classes/debug
 * com.example.hp.knowlgdemo.ui.NDKActivity
 */
public class NDKActivity extends AppCompatActivity {

    static {
        System.loadLibrary("Ndkhello");//之前在build.gradle里面设置的so名字，必须一致
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        TextView tv = (TextView) findViewById(R.id.tv);
//        tv.setText(getStringFromB());


    }
}
