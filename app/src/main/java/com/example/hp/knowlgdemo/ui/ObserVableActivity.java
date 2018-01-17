package com.example.hp.knowlgdemo.ui;

import android.database.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.knowlgdemo.R;

public class ObserVableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obser_vable);
        Observable<String> observable=new Observable<String>() {
            @Override
            public void registerObserver(String observer) {
//                super.registerObserver(observer);

            }

            @Override
            public void unregisterObserver(String observer) {
                super.unregisterObserver(observer);
            }

            @Override
            public void unregisterAll() {
                super.unregisterAll();
            }
        };
        observable.registerObserver("ssssssss");
    }
}
