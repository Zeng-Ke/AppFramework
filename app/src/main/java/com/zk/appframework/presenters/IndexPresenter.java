package com.zk.appframework.presenters;

import com.zk.android_utils.base.BasePresenter;
import com.zk.android_utils.base.IView;

/**
 * author: ZK.
 * date:   On 2018/6/11.
 */
public class IndexPresenter extends BasePresenter<IndexPresenter.IndexView> {


    public IndexPresenter(IndexView iView) {
        super(iView);
    }

    public  interface  IndexView extends IView{

    }


}
