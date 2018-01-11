package com.example.hp.knowlgdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hp.knowlgdemo.R;

public class CollapsingToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);
        Toolbar ttt = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(ttt);
    }
}
