package com.example.hp.knowlgdemo;

import android.app.Application;
import android.os.StrictMode;
import android.support.v4.*;
import android.support.v4.BuildConfig;

/**
 * Created by HP on 2018/1/13.
 */

public class My_Application extends Application {
    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG){
            //BlockkCanary   可以用来监控数据应用主线程的卡顿
            //LeakCanary     可以用来监控应用的内存泄露

            //  ANR的检测   ----》StrictMode检测工具  ---》检测代码中是否存在违规操作的工具类
            //开启线程模式
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    //.detectAll()  //检测所有
                    .detectCustomSlowCalls()   //检测自定义耗时操作
                    .detectDiskReads()    //检测是否存在磁盘读取操作
                    .detectDiskWrites()    //检测是否存在磁盘写入操作
                    .detectNetwork()    //检测是否存在网络操作
                    .penaltyLog()
                    .build());
            //开启虚拟机模式
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                    .detectAll()   //检测所有
                    .detectActivityLeaks()    //检测是否存在activity泄露
                    .detectLeakedClosableObjects()   //检测是否存在未关闭的Closable对象泄露
                    .detectLeakedSqlLiteObjects()    //检测是否存在Sqlite对象泄露
//                    .setClassInstanceLimit()   //检测类实例个数是否操作限制
                    .penaltyLog()
                    .build());
        }
        super.onCreate();
    }
}
