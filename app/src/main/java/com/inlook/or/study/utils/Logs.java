package com.inlook.or.study.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * desc Logs
 *
 * @author: or
 * @since: on 2016/5/18.
 */
public class Logs {

    private static boolean mLogEnable = true;
    private static Boolean MYLOG_SWITCH = false; // 日志文件总开关
    private static char MYLOG_TYPE = 'v';// 输入日志类型，w代表只输出告警信息等，v代表输出所有信息
    private static SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss SSS");

    public static void w(String tag, Object msg) { // 警告信息
        if (mLogEnable) {
            log(tag, msg.toString(), 'w');
        }
    }

    public static void e(String tag, Object msg) { // 错误信息
        if (mLogEnable) {
            log(tag, msg.toString(), 'e');
        }
    }

    public static void d(String tag, Object msg) {// 调试信息
        if (mLogEnable) {
            log(tag, msg.toString(), 'd');
        }
    }

    public static void i(String tag, Object msg) {//
        if (mLogEnable) {
            log(tag, msg.toString(), 'i');
        }
    }

    public static void v(String tag, Object msg) {
        if (mLogEnable) {
            log(tag, msg.toString(), 'v');
        }
    }

    public static void w(String tag, String text) {
        if (mLogEnable) {
            log(tag, text, 'w');
        }
    }

    public static void e(String tag, String text) {
        if (mLogEnable) {
            log(tag, text, 'e');
        }
    }

    public static void d(String tag, String text) {
        if (mLogEnable) {
            log(tag, text, 'd');
        }
    }

    public static void i(String tag, String text) {
        if (mLogEnable) {
            log(tag, text, 'i');
        }
    }

    public static void v(String tag, String text) {
        if (mLogEnable) {
            log(tag, text, 'v');
        }
    }

    /**
     * 根据tag, msg和等级，输出日志
     *
     * @param tag
     * @param msg
     * @param level
     * @return void
     * @since v 1.0
     */
    private static void log(String tag, String msg, char level) {
        if (MYLOG_SWITCH) {
            if ('e' == level && ('e' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) { // 输出错误信息
                Log.e(tag, msg);
            } else if ('w' == level && ('w' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) {
                Log.w(tag, msg);
            } else if ('d' == level && ('d' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) {
                Log.d(tag, msg);
            } else if ('i' == level && ('d' == MYLOG_TYPE || 'v' == MYLOG_TYPE)) {
                Log.i(tag, msg);
            } else {
                Log.v(tag, msg);
            }
        } else {
            System.out.println(formatter.format(new Date()) + " - " + tag + " : " + msg);
        }
    }

}
