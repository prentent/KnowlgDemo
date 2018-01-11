package com.example.hp.knowlgdemo.ui;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hp.knowlgdemo.R;

public class BootSheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot_sheet2);
//        View bottomSheet =  findViewById(R.id.bottom_sheet);
//        BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);
//        int state = behavior.getState();
//        if (state == BottomSheetBehavior.STATE_EXPANDED) {
//            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//        }else{
//            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        }
    }
}
