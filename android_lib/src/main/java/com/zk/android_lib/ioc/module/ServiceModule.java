package com.zk.android_lib.ioc.module;

import com.zk.android_lib.api.IFirstApi;
import com.zk.android_lib.service.FirstService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * author: ZK.
 * date:   On 2018-07-09.
 */
@Module(includes = {ApiModule.class})
public class ServiceModule {

    @Provides
    public FirstService provideFirstService(IFirstApi firstApi) {
        return new FirstService(firstApi);
    }

}
