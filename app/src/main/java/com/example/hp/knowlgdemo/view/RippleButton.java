package com.example.hp.knowlgdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by HP on 2017/12/24.
 */

public class RippleButton extends android.support.v7.widget.AppCompatButton {

    private MyRippleDrawable rippleDrawable;

    public RippleButton(Context context) {
        super(context);
        init(context);
    }

    public RippleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RippleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        rippleDrawable = new MyRippleDrawable();
        setBackgroundDrawable(rippleDrawable);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rippleDrawable.draw(canvas);
    }
}
