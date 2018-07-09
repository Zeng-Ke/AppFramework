package com.zk.android_lib.ioc.component;

import com.zk.android_lib.ioc.module.ServiceModule;
import com.zk.android_lib.presenters.HomePageActivityPresenter;
import com.zk.android_lib.presenters.MainPresenter;


import dagger.Component;

/**
 * author: ZK.
 * date:   On 2018-07-09.
 */
@Component(modules = ServiceModule.class)
public interface PresenterComponent {

    void inject(HomePageActivityPresenter presenter);

    void inject(MainPresenter presenter);

}
