package com.zk.android_lib.ioc.module;

import com.zk.android_lib.api.IFirstApi;
import com.zk.android_lib.http.HttpCreator;

import dagger.Module;
import dagger.Provides;

/**
 * author: ZK.
 * date:   On 2018-07-09.
 */
@Module
public class ApiModule {


    @Provides
    public IFirstApi provideFirstApi() {
        return HttpCreator.createApi(IFirstApi.class);
    }


}
