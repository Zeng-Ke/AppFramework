package com.zk.java_utils.log;


/**
 * author: ZK.
 * date:   On 2018/6/6.
 */
public class LogUtil {

    private static LogInterface mLogInterface;
    private static boolean mIsDebug;

    private LogUtil() {
    }

    public static void init(boolean isDebug, LogInterface logInterface) {
        mLogInterface = logInterface;
        mIsDebug = isDebug;

    }

    public static void d(Object object) {
        if (mIsDebug)
            mLogInterface.d(object);
    }

    public static void d(String tag, Object object) {
        if (mIsDebug)
            mLogInterface.d(tag, object);
    }

    public static void v(String msg) {

        if (mIsDebug)
            mLogInterface.v(msg);
    }

    public static void v(String tag, String msg) {
        if (mIsDebug)
            mLogInterface.v(tag, msg);
    }

    public static void i(String msg) {
        if (mIsDebug)
            mLogInterface.i(msg);
    }

    public static void i(String tag, String msg) {
        if (mIsDebug)
            mLogInterface.i(tag, msg);
    }

    public static void w(String msg) {
        if (mIsDebug)
            mLogInterface.w(msg);
    }

    public static void w(String tag, String msg) {
        if (mIsDebug)
            mLogInterface.w(tag, msg);
    }


    public static void e(String msg) {
        if (mIsDebug)
            mLogInterface.e(msg);
    }

    public static void e(String msg, Throwable throwable) {
        if (mIsDebug)
            mLogInterface.e(msg, throwable);
    }

    public void json(String json) {
        if (mIsDebug)
            mLogInterface.json(json);
    }

    public void json(String tag, String json) {
        if (mIsDebug)
            mLogInterface.json(tag, json);
    }
}
