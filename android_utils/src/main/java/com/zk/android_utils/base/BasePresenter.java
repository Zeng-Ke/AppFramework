package com.zk.android_utils.base;

/**
 * author: ZK.
 * date:   On 2018/6/7.
 */
public class BasePresenter<T extends IView> {

    private T mIView;

    public BasePresenter(T iView) {
        mIView = iView;
    }


    protected T getView() {
        return mIView;
    }


    protected void onViewCreate() {
    }

    protected void onViewStart() {
    }

    protected void onViewResume() {
    }

    public void onViewPause() {
    }

    private void onViewRestart() {
    }

    protected void onViewStop() {
    }

    protected void onViewDestory() {
    }


    protected void detachView() {
        mIView = null;
    }


}
