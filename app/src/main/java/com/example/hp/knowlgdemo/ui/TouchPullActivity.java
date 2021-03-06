package com.example.hp.knowlgdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.hp.knowlgdemo.R;
import com.example.hp.knowlgdemo.view.TouchPullView;
import com.example.hp.knowlgdemo.view.TouchPullView2;

public class TouchPullActivity extends AppCompatActivity  {
    private float mTouchStartY;
    private static final float TOUCH_MOVE_MAX_Y = 600; //y轴移动的最大值
    private TouchPullView touchview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_pull);
        touchview = (TouchPullView) findViewById(R.id.touchview);
    }

    private float mTransY = 0.0f;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int actionMasked = event.getActionMasked();
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                mTouchStartY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                if (y >= mTouchStartY) {  //表示向下移动
                    float moveSize = y - mTouchStartY;
                    float progress = moveSize > TOUCH_MOVE_MAX_Y ?
                            1 : moveSize / TOUCH_MOVE_MAX_Y;   //计算进度值
                    touchview.setProgress(progress);
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                touchview.release();
                return true;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

}
