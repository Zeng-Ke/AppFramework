package com.zk.appframework.app;

import android.app.Application;

import com.orhanobut.logger.PrettyFormatStrategy;
import com.zk.android_utils.LogManager;
import com.zk.appframework.BuildConfig;
import com.zk.java_utils.log.LogUtil;

/**
 * author: ZK.
 * date:   On 2018/6/6.
 */
public class MApplication extends Application {

    public static boolean isDebug = BuildConfig.DEBUG;

    @Override
    public void onCreate() {
        super.onCreate();


        LogUtil.init(LogManager.initAndReturn(PrettyFormatStrategy
                .newBuilder()
                .showThreadInfo(true)//是否显示线程信息
                .methodCount(2)  //显示的方法行数
                .methodOffset(5)//内部方法调用偏移量
                .tag("AppFramework")
                .build()));


    }
}
