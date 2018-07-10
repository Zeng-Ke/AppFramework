package com.zk.android_lib.cache;

import com.zk.android_utils.cache.sp.SharePreferenceHandler;
import com.zk.android_utils.utils.AppUtils;

/**
 * author: ZK.
 * date:   On 2018-07-10.
 */
public class BaseSpCache {

    protected SharePreferenceHandler mSharePreferenceHandler;

    public BaseSpCache(String fileName) {
        mSharePreferenceHandler = new SharePreferenceHandler(AppUtils.getApp(), fileName);
    }




}
