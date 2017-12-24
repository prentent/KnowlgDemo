package com.example.hp.knowlgdemo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * banner适配器
 */

public class BannerAdapter extends PagerAdapter {
    private Context context;
    private int[] imgRes;

    public BannerAdapter(Context contexts, int[] imgRes) {
        this.context = contexts;
        this.imgRes = imgRes;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
//        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(context);
        int i = position % imgRes.length;
        iv.setImageResource(imgRes[i]);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        iv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        container.addView(iv);
        return iv;
    }
}
