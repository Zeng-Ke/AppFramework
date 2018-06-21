package com.zk.appframework.presenters;

import com.zk.android_utils.base.BasePresenter;
import com.zk.android_utils.base.IView;

/**
 * author: ZK.
 * date:   On 2018/6/7.
 */
public class MainPresenter extends BasePresenter<MainPresenter.MainView> {


    public MainPresenter(MainView iView) {
        super(iView);
    }

    public interface MainView extends IView {
    }


}
