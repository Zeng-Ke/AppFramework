package com.zk.android_utils.manager;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.zk.java_utils.log.LogInterface;

/**
 * author: ZK.
 * date:   On 2018/6/6.
 */
public class LogManager implements LogInterface {

    private static LogManager mInstance = new LogManager();

    private LogManager() {
    }


    public static LogManager initAndReturn(FormatStrategy strategy) {
        Logger.addLogAdapter(strategy == null ? new AndroidLogAdapter() : new AndroidLogAdapter(strategy));
        return mInstance;
    }


    @Override
    public void d(Object object) {
        Logger.d(object);
    }

    @Override
    public void d(String tag, Object object) {
        Logger.t(tag).d(object);
    }

    @Override
    public void v(String msg) {
        Logger.v(msg, "");
    }

    @Override
    public void v(String tag, String msg) {
        Logger.t(tag).v(msg, "");
    }


    @Override
    public void i(String msg) {
        Logger.i(msg);
    }

    @Override
    public void i(String tag, String msg) {
        Logger.t(tag).i(msg, "");
    }

    @Override
    public void w(String msg) {
        Logger.w(msg);
    }

    @Override
    public void w(String tag, String msg) {
        Logger.t(tag).w(msg, "");
    }

    @Override
    public void e(String msg) {
        Logger.e(msg, "");
    }

    @Override
    public void e(String tag, Throwable throwable) {
        Logger.t(tag).e(throwable, tag, "");
    }

    @Override
    public void json(String json) {
        Logger.json(json);
    }

    @Override
    public void json(String tag, String json) {
        Logger.t(tag).json(json);
    }
}
