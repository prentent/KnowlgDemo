package com.example.hp.knowlgdemo.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hp.knowlgdemo.R;

public class AnmaitorActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anmaitor);
        img = (ImageView) findViewById(R.id.imageView2);
        btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        ObjectAnimator.ofFloat(img,"translationX",0.0f,200.0f).setDuration(1000).start();
//        ObjectAnimator.ofFloat(img,"translationY",0.0f,200.0f).setDuration(1000).start();
//        ObjectAnimator.ofFloat(img,"rotation",0.0f,360.f).setDuration(1000).start();
//        PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX", 0.0f, 200.0f);
//        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY", 0.0f, 200.0f);
//        PropertyValuesHolder rotation = PropertyValuesHolder.ofFloat("rotation", 0.0f, 360.f);
//        ObjectAnimator.ofPropertyValuesHolder(img,translationX,translationY,rotation).setDuration(1000).start();
//        ObjectAnimator translationX = ObjectAnimator.ofFloat(img, "translationX", 0.0f, 200.0f);
//        ObjectAnimator translationY = ObjectAnimator.ofFloat(img, "translationY", 0.0f, 200.0f);
//        ObjectAnimator rotation = ObjectAnimator.ofFloat(img, "rotation", 0.0f, 360.f);
//        AnimatorSet set=new AnimatorSet();
//        set.play(translationX).with(translationY);
//        set.play(rotation).after(translationX);
////        set.playTogether(translationX,translationY,rotation);
//        set.setDuration(1000);
//        set.start();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
        valueAnimator.setDuration(5000);
        valueAnimator.setInterpolator(new DecelerateInterpolator(this,null));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer ints = (Integer) animation.getAnimatedValue();
                btn.setText(ints + "");
            }
        });
        valueAnimator.start();


    }
}
