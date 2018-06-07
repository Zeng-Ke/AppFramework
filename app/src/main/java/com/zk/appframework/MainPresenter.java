package com.zk.appframework;

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
        void  returnData(String s);
    }

    public  void  loadData(){
        String s = "presenter";
        getView().returnData(s);
    }
}
