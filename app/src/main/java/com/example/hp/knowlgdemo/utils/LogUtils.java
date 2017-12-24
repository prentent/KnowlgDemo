package com.example.hp.knowlgdemo.utils;

import android.util.Log;

/**
 * Log工具类
 */

public class LogUtils {

    private static final boolean isLog = true;

    /*
    打印错误日志
     */
    public static void e(String str, String str1) {
        if (isLog)
            Log.e(str, str1);
    }

    /*
    打印正确日志
     */
    public static void i(String str, String str1) {
        if (isLog)
            Log.i(str, str1);
    }
}
