package com.example.hp.knowlgdemo.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private CrashHandler() {
    }

    private Thread.UncaughtExceptionHandler exceptionHandler;
    private Context context;

    private static CrashHandler crashHandler = null;
    //设备存储异常信息，及错误信息
    private Map<String, String> mInfo = new HashMap<String, String>();
    //文件日期格式
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static CrashHandler getInstence() {
        if (crashHandler == null) {
            synchronized (CrashHandler.class) {
                if (crashHandler == null)
                    crashHandler = new CrashHandler();
            }
        }
        return crashHandler;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        this.context = context;
        exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置默认处理类
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //1：收集错误信息
        //2：保存错误信息
        //3：上传到服务器
        if (!handleException(e)) {
            //未处理，调用系统默认处理器处理
            if (exceptionHandler != null) {
                exceptionHandler.uncaughtException(t, e);
            }
        } else {
            //已近人为处理
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            Process.killProcess(Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 人为处理异常
     *
     * @param e
     * @return true 未处理 false 已处理
     */
    private boolean handleException(Throwable e) {
        if (e == null)
            return false;
        //toast提示一下
        new Thread() {
            @Override
            public void run() {
//                super.run();
                Looper.prepare();
                Toast.makeText(context, "UncaugthException", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        //收集错误信息
        collectErrorInfo();
        //报存错误信息
        saveErrorInfo(e);
        return false;
    }

    private void saveErrorInfo(Throwable e) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> map : mInfo.entrySet()) {
            String keyName = map.getKey();
            String value = map.getValue();
            stringBuffer.append(keyName + "=" + value + "\n");
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        if (cause != null) {
            cause.printStackTrace(printWriter);
            cause = e.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        stringBuffer.append(result);
        long curTime = System.currentTimeMillis();
        String time = dateFormat.format(curTime);
        String fileName = "crash" + "." + time + ",log";
        //有无sd卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = "/sdcard/crash/";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(path + fileName);
                out.write(stringBuffer.toString().getBytes());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }


    }

    private void collectErrorInfo() {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionNmae = TextUtils.isEmpty(pi.versionName) ? "未设置潘奔名称" : pi.versionName;
                String versionCode = pi.versionCode + "";
                mInfo.put("versionNmae", versionNmae);
                mInfo.put("versionCode", versionCode);
            }
            Field[] fields = Build.class.getFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    mInfo.put(field.getName(), field.get(null).toString());
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
