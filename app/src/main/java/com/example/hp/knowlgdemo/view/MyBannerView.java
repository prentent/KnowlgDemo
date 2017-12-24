package com.example.hp.knowlgdemo.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.example.hp.knowlgdemo.R;
import com.example.hp.knowlgdemo.adapter.BannerAdapter;
import com.example.hp.knowlgdemo.utils.AlphaPageTransformer;

import java.lang.reflect.Field;

/**
 * Created by HP on 2017/12/23.
 */

public class MyBannerView extends LinearLayout implements ViewPager.OnPageChangeListener {
    private int[] imgRes = {
            R.mipmap.image1, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4,
            R.mipmap.image5, R.mipmap.image6, R.mipmap.image7, R.mipmap.image8
    };
    private ViewPager vpr;
    private int currIndex = 0;
    private Context context;
    private BannerAdapter adapter;
    private GestureDetector gestureDetector;

    public MyBannerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MyBannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyBannerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        gestureDetector = new GestureDetector(context, new YGestureDetector());
        vpr = new ViewPager(context);
        LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400);
        params.setMargins(100, 0, 100, 0);
        vpr.setLayoutParams(params);
        vpr.addOnPageChangeListener(this);
        //设置page间的间隔
        vpr.setPageMargin(30);
        //设置缓存的页面数
        vpr.setOffscreenPageLimit(3);
        vpr.setClipChildren(false);
        //添加自定义动画
        vpr.setPageTransformer(true, new AlphaPageTransformer());
        adapter = new BannerAdapter(context, imgRes);
        vpr.setAdapter(adapter);
        if (imgRes.length > 1)
            vpr.setCurrentItem(Integer.MAX_VALUE / 2);
        // 初始化Scroller
        initViewPagerScroll();

        addView(vpr);
        setClipChildren(false);
    }

    /**
     * 设置ViewPager的滑动速度
     */
    private void initViewPagerScroll() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            ViewPagerScroller mViewPagerScroller = new ViewPagerScroller(
                    vpr.getContext());
            mScroller.set(vpr, mViewPagerScroller);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset == 0.0) {
            if (position == 1) {
                vpr.setCurrentItem(Integer.MAX_VALUE - 1, false);
            } else if (position == Integer.MAX_VALUE - 2) {
                vpr.setCurrentItem(1, false);
            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class YGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return (Math.abs(distanceY)) > (Math.abs(distanceX));
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) &&
                gestureDetector.onTouchEvent(ev);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    int mCurrentItem = vpr.getCurrentItem();
                    mCurrentItem++;
                    if (mCurrentItem == adapter.getCount() - 1) {
                        mCurrentItem = 0;
                        vpr.setCurrentItem(mCurrentItem, false);
                        handler.sendEmptyMessageDelayed(1, 3000);
                    } else {
                        vpr.setCurrentItem(mCurrentItem);
                        handler.sendEmptyMessageDelayed(1, 3000);
                    }
                    break;
            }
        }
    };

    public void startBanner() {
        handler.sendEmptyMessageDelayed(1, 3000);
    }

    public void stopBanner() {
        handler.removeMessages(1);
    }

    /**
     * ＊由于ViewPager 默认的切换速度有点快，因此用一个Scroller 来控制切换的速度
     * <p>而实际上ViewPager 切换本来就是用的Scroller来做的，因此我们可以通过反射来</p>
     * <p>获取取到ViewPager 的 mScroller 属性，然后替换成我们自己的Scroller</p>
     */
    public static class ViewPagerScroller extends Scroller {
        private int mDuration = 800;// ViewPager默认的最大Duration 为600,我们默认稍微大一点。值越大越慢。
        private boolean mIsUseDefaultDuration = false;

        public ViewPagerScroller(Context context) {
            super(context);
        }

        public ViewPagerScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public ViewPagerScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mIsUseDefaultDuration ? duration : mDuration);
        }

        public void setUseDefaultDuration(boolean useDefaultDuration) {
            mIsUseDefaultDuration = useDefaultDuration;
        }

        public boolean isUseDefaultDuration() {
            return mIsUseDefaultDuration;
        }

        public void setDuration(int duration) {
            mDuration = duration;
        }


        public int getScrollDuration() {
            return mDuration;
        }
    }
}
