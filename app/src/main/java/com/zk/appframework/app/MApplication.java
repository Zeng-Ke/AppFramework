package com.zk.appframework.app;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zk.android_lib.presenters.base.BaseApplication;
import com.zk.appframework.BuildConfig;

/**
 * author: ZK.
 * date:   On 2018/6/6.
 */
public class MApplication extends BaseApplication {


    @Override
    public void onCreate() {
        setDebug(BuildConfig.DEBUG);
        super.onCreate();
        ARouter.init(this);

    }


}
