package com.example.hp.knowlgdemo.ui;

import android.os.StrictMode;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hp.knowlgdemo.R;
import com.example.hp.knowlgdemo.utils.LogUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolActivity extends AppCompatActivity {

    //核心线程数，除非allowCoreThreadTimeOut被设置为true，否则它闲着也不会死
    private static final int corePoolSize=1;
    //最大线程数，活动线程数量超过它，后续任务就会排队
    private static final int maximumPoolSize=10;
    //超时时长，作用于非核心线程（allowCoreThreadTimeOut被设置为true时也会同时作用于核心线程），闲置超时便被回收
    private static final long keepAliveTime=60;
    //枚举类型，设置keepAliveTime的单位，有TimeUnit.MILLISECONDS（ms）、TimeUnit. SECONDS（s）等
    private static final TimeUnit unit=TimeUnit.MICROSECONDS;
    //缓冲任务队列，线程池的execute方法会将Runnable对象存储起来
    private  BlockingQueue<Runnable> workQueue=new LinkedBlockingQueue<>(105);
    //线程工厂接口，只有一个new Thread(Runnable r)方法，可为线程池创建新线程
    private  ThreadFactory threadFactory;
    private ExecutorService executorService_singleThreadPool;
    private ExecutorService executorService_fixThreadPool;
    private ExecutorService executorService;
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);
    }

    public void onClick_TP(View view){
        ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(
                corePoolSize,//核心线程数，除非allowCoreThreadTimeOut被设置为true，否则它闲着也不会死
                10,//最大线程数，活动线程数量超过它，后续任务就会排队
                1, //超时时长
                TimeUnit.SECONDS, // 超时时长的时分秒等等
                workQueue//缓冲任务队列,有多少个，则一次执行多少个
        );

        for (int i = 0; i < 30; i++) {
            final int finalI = i;
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    LogUtils.e("========",""+ finalI);
                    SystemClock.sleep(500);
                }
            };
            poolExecutor.execute(runnable);
        }
       // poolExecutor.shutdown();
        LogUtils.e("====", "run: ----1111");
    }

    /*
    l只有核心线程，并且数量固定的，也不会被回收，所有线程都活动时，
    因为队列没有限制大小，新任务会等待执行。
    FixThreadPool其实就像一堆人排队上公厕一样，可以无数多人排队，
    但是厕所位置就那么多，而且没人上时，厕所也不会被拆迁
     */
    public void FixThreadPool(View view){
        executorService_fixThreadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 30; i++) {
            final int finalI = i;
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    LogUtils.e("========",""+ finalI);
                    SystemClock.sleep(500);
                }
            };
            executorService_fixThreadPool.execute(runnable);
        }
     //   executorService.shutdown();
        LogUtils.e("====", "run: ----1111");
    }

    public void FixThreadPool_two(View view){
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    LogUtils.e("========",""+ finalI);
                    SystemClock.sleep(500);
                }
            };
            executorService_fixThreadPool.execute(runnable);
        }
    }


    /*
    只有一个核心线程，确保所有任务都在同一线程中按顺序完成。因此不需要处理线程同步的问题。
    公厕里只有一个坑位，先来先上。为什么只有一个坑位呢，因为这个公厕是收费的，
    收费的大爷上年纪了，只能管理一个坑位，多了就管不过来了（线程同步问题）。
     */
    public void SingleThreadPool(View view){
        executorService_singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 30; i++) {
            final int finalI = i;
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    LogUtils.e("========",""+ finalI);
                    SystemClock.sleep(500);
                }
            };
            executorService_singleThreadPool.execute(runnable);
        }
    //    executorService.shutdown();
        LogUtils.e("====", "run: ----1111");
    }

    public void SingleThreadPool_two(View view){
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    LogUtils.e("========",""+ finalI);
                    SystemClock.sleep(500);
                }
            };
            executorService_singleThreadPool.execute(runnable);
        }
    }

    /*
    只有非核心线程，最大线程数非常大，所有线程都活动时，会为新任务创建新线程，
    否则利用空闲线程（60s空闲时间，过了就会被回收，所以线程池中有0个线程的可能）处理任务。
    比较适合执行大量的耗时较少的任务。喝咖啡人挺多的，喝的时间也不长
     */
    public void CachedThreadPool(View view){
        executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 30; i++) {
            final int finalI = i;
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    LogUtils.e("========",""+ finalI);
                    SystemClock.sleep(500);
                }
            };
            executorService.execute(runnable);
        }
        LogUtils.e("====", "run: ----1111");
    }

    public void CachedThreadPool_two(View view){
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    LogUtils.e("========",""+ finalI);
                    SystemClock.sleep(500);
                }
            };
            executorService.execute(runnable);
        }
    }

    /*
    延时任务
     */
    public void ScheduledThreadPool(View view){
        scheduledExecutorService = Executors.newScheduledThreadPool(3);
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                LogUtils.e("====", "run: ----");
            }
        };
        scheduledExecutorService.schedule(runnable, 1, TimeUnit.SECONDS);
        LogUtils.e("====", "run: ----1111");
    }

    public void ScheduledThreadPool_two(){
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                LogUtils.e("====", "run: ----");
            }
        };
        scheduledExecutorService.schedule(runnable, 1, TimeUnit.SECONDS);
    }

    /*
    延时并没过一定时间执行任务
     */
    public void ScheduledThreadPool_(View view){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                LogUtils.e("====", "run: ----");
            }
        };
        scheduledExecutorService.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        LogUtils.e("====", "run: ----1111");
    }

}
