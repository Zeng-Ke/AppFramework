package com.zk.android_utils.base;

import android.app.Application;

import com.orhanobut.logger.PrettyFormatStrategy;
import com.zk.android_utils.manager.ActivityManager;
import com.zk.android_utils.manager.LogManager;
import com.zk.java_utils.log.LogUtil;

/**
 * author: ZK.
 * date:   On 2018/6/25.
 */
public class BaseApplication extends Application {

    private static boolean mIsDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.init(mIsDebug, LogManager.initAndReturn(PrettyFormatStrategy
                .newBuilder()
                .showThreadInfo(true)//是否显示线程信息
                .methodCount(2)  //显示的方法行数
                .methodOffset(5)//内部方法调用偏移量
                .tag("AppFramework")
                .build()));
    }

    protected void setDebug(boolean isDebug) {
        mIsDebug = isDebug;
    }

    public static boolean IsDebug() {
        return mIsDebug;
    }


    public static ActivityManager getActivityManager() {
        return ActivityManager.getInstance();
    }


}
