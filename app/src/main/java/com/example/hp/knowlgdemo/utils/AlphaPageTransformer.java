package com.example.hp.knowlgdemo.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * ViewPager滑动动画
 */

public class AlphaPageTransformer implements ViewPager.PageTransformer {
    private static final float DEFAULT_MIN_ALPHA = 0.5f;
    private float mMinAlpha = DEFAULT_MIN_ALPHA;
    private static final float DEFAULT_MAX_ROTATE = 15.0f;
    private float mMaxRotate = DEFAULT_MAX_ROTATE;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            page.setAlpha(mMinAlpha);
            page.setRotation(mMaxRotate * -1);
            page.setPivotX(page.getWidth());
            page.setPivotY(page.getHeight());
        } else if (position <= 1) { // [-1,1]

            if (position < 0) //[0，-1]
            {
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 + position);
                page.setAlpha(factor);
                page.setPivotX(page.getWidth() * (0.5f + 0.5f * (-position)));
                page.setPivotY(page.getHeight());
                page.setRotation(mMaxRotate * position);
            } else//[1，0]
            {
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 - position);
                page.setAlpha(factor);
                page.setPivotX(page.getWidth() * 0.5f * (1 - position));
                page.setPivotY(page.getHeight());
                page.setRotation(mMaxRotate * position);
            }
        } else { // (1,+Infinity]
            page.setAlpha(mMinAlpha);
            page.setRotation(mMaxRotate);
            page.setPivotX(page.getWidth() * 0);
            page.setPivotY(page.getHeight());
        }
    }
}
