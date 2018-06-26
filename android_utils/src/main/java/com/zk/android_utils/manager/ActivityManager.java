package com.zk.android_utils.manager;

import com.zk.android_utils.base.BaseActivity;

/**
 * author: ZK.
 * date:   On 2018/6/25.
 */
public class ActivityManager {
    private static ActivityManager instance;

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (instance == null)
            instance = new ActivityManager();
        return instance;
    }

    public BaseActivity getLastResumeActivity() {
        return ActivityUtil.getLastResumeActivity();
    }

}
