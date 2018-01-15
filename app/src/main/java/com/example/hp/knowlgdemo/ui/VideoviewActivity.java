package com.example.hp.knowlgdemo.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.knowlgdemo.R;
import com.example.hp.knowlgdemo.utils.DensityUtils;
import com.example.hp.knowlgdemo.utils.LogUtils;
import com.example.hp.knowlgdemo.utils.ToastUtils;

import java.io.File;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;

public class VideoviewActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_back, img_play, img_full;
    private SeekBar seek_bar;
    private TextView seek_progress;

    private VideoView video;


    private static final String path = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "Movies/xxx.mp4";
    private static final String Url_path = "http://www.wooyun.site/1987.mp4";
    private static final int UPDETE = 1;
    private boolean isFullScreen;
    private RelativeLayout videoLayout;

    private AudioManager audioManager;//音量控制
    private int maxVolum;
    private int curVolum;
    private int screenWidth;
    private int screenHeight;

    private float lastX = 0;
    private float lastY = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview);
        initView();
        initEvent();

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDETE:
                    long currentPosition = video.getCurrentPosition();
                    long duration = video.getDuration();
                    seek_bar.setProgress((int) currentPosition);
                    seek_progress.setText(updentTime(currentPosition) + "/" + updentTime(duration));
                    if (currentPosition >= duration) {
                        return;
                    }
                    sendEmptyMessageDelayed(UPDETE, 500);
                    break;
            }

        }
    };

    /**
     * View事件
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initEvent() {
        img_back.setOnClickListener(this);
        img_play.setOnClickListener(this);
        img_full.setOnClickListener(this);
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                video.start();
                seek_bar.setMax((int) video.getDuration());
                seek_bar.setProgress(0);
                handler.sendEmptyMessageDelayed(UPDETE, 500);
            }
        });
        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (video != null && video.isPlaying()) {
                    video.pause();
                }
                handler.removeMessages(UPDETE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (video != null) {
                    video.seekTo(seekBar.getProgress());
                    handler.sendEmptyMessageDelayed(UPDETE, 500);
                }
            }
        });

    }

    private String updentTime(long time) {
        time = time / 1000;
        long minutes = time % 60;
        long seconds = time / 60;
        return minutes + ":" + seconds;
    }

    /**
     * View注册
     */
    private void initView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        img_play = (ImageView) findViewById(R.id.img_play);
        seek_bar = (SeekBar) findViewById(R.id.seek_bar);
        img_full = (ImageView) findViewById(R.id.img_full);
        seek_progress = (TextView) findViewById(R.id.seek_progress);
        videoLayout = (RelativeLayout) findViewById(R.id.videoLayout);
        video = (VideoView) findViewById(R.id.video);

        //音量初始化  待后续完善
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        maxVolum = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        curVolum = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);


        //获取屏幕的宽度
        WindowManager manager = getWindowManager();
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        video.setVideoPath(path);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_play:
                if (video != null) {
                    if (video.isPlaying()) {
                        img_play.setImageResource(R.mipmap.ic_stop);
                        video.pause();
                    } else {
                        img_play.setImageResource(R.mipmap.ic_play);
                        video.start();
                    }
                }
                break;
            case R.id.img_full:
                if (isFullScreen){
                    img_full.setImageResource(R.mipmap.ic_full);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }else {
                    img_full.setImageResource(R.mipmap.ic_full_no);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                break;
        }
    }

    /**
     * 设置缩放比例
     */
    private void setVedioViewScale(int width, int height) {
        ViewGroup.LayoutParams layoutParams = video.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        video.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams layoutParams1 = videoLayout.getLayoutParams();
        layoutParams1.width = width;
        layoutParams1.height = height;
        videoLayout.setLayoutParams(layoutParams1);
    }

    /**
     * 控制屏幕显示的大小
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //横屏
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setVedioViewScale(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            isFullScreen = true;
            //移除半屏状态
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            //添加全屏状态
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            LogUtils.e("==========","横屏");
        } else {
            //竖屏
            setVedioViewScale(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(this,250));
            isFullScreen = false;
            //移除全屏状态
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //添加半屏状态
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            LogUtils.e("==========","竖屏");
        }
    }

    /**
        *
        * 设置屏幕亮度 lp = 0 全暗 ，lp= -1,根据系统设置， lp = 1; 最亮
        *
        屏幕最大亮度为255。
        屏幕最低亮度为0。
        屏幕亮度值范围必须位于：0～255。
     */
    public void setBrightness(float brightness) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.screenBrightness = lp.screenBrightness + brightness / 255.0f; //当前亮度+ 滑动值/255
        if (lp.screenBrightness > 1) {
            lp.screenBrightness = 1;
        } else if (lp.screenBrightness < 0.1) {
            lp.screenBrightness = (float) 0.1;
        }
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.removeMessages(UPDETE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.sendEmptyMessageDelayed(UPDETE, 500);
    }
}
