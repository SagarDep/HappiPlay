package com.example.mac.myapplication.utils;

import android.util.Log;

/**
 * Created by happi on 16/1/7.
 */
public class LogUtils {
    public static int open = 0;//0：打开注释 1：关闭注释

    public static void i(String className,String msg) {
        if (open == 0) {
            Log.i(className,msg);
        }
    }

    public static void e(String className,String msg) {
        if (open == 0) {
            Log.e(className,msg);
        }
    }
}
