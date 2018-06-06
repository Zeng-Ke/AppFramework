package com.zk.java_utils.log;


/**
 * author: ZK.
 * date:   On 2018/6/6.
 */
public class LogUtil {

    private static LogInterface mLogInterface;

    private LogUtil() {

    }

    public static void init(LogInterface logInterface) {
        mLogInterface = logInterface;
    }


    public static void d(Object object) {
        mLogInterface.d(object);
    }

    public static void d(String tag, Object object) {
        mLogInterface.d(tag, object);
    }


    public static void v(String msg) {
        mLogInterface.v(msg);
    }

    public static void v(String tag, String msg) {
        mLogInterface.v(tag, msg);
    }


    public static void i(String msg) {
        mLogInterface.i(msg);
    }

    public static void i(String tag, String msg) {
        mLogInterface.i(tag, msg);
    }

    public static void w(String msg) {
        mLogInterface.w(msg);
    }

    public static void w(String tag, String msg) {
        mLogInterface.w(tag, msg);
    }


    public static void e(String msg) {
        mLogInterface.e(msg);
    }

    public static void e(String msg, Throwable throwable) {
        mLogInterface.e(msg, throwable);
    }
}
