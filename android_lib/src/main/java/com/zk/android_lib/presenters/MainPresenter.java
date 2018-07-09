package com.zk.android_lib.presenters;

import com.zk.android_lib.ioc.component.PresenterComponent;
import com.zk.android_lib.presenters.base.IocBasePresenter;
import com.zk.android_lib.service.FirstService;
import com.zk.android_utils.base.IView;

import javax.inject.Inject;

/**
 * author: ZK.
 * date:   On 2018/6/7.
 */
public class MainPresenter extends IocBasePresenter<MainPresenter.MainView> {


    @Inject
    FirstService mFirstService;

    public MainPresenter(MainView iView) {
        super(iView);

    }

    @Override
    public void componentInject(PresenterComponent component) {
        component.inject(this);
    }

    public interface MainView extends IView {
    }


}
