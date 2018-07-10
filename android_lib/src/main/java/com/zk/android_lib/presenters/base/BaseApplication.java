package com.zk.android_lib.presenters.base;

import com.zk.android_utils.cache.disk.DisklruCacheProvider;
import com.zk.android_utils.http.RxCache;

/**
 * author: ZK.
 * date:   On 2018-07-09.
 */
public class BaseApplication extends com.zk.android_utils.base.BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        RxCache.init("httpdata", 50, 2, DisklruCacheProvider.SIZEUNIT.MB);

    }


}

