package com.zk.android_lib.http.base.callbacker;

import com.zk.android_lib.http.base.IUnbinderManager;
import com.zk.android_utils.base.BaseActivity;
import com.zk.android_utils.base.BaseApplication;

import io.reactivex.disposables.Disposable;

/**
 * author: ZK.
 * date:   On 2018/6/25.
 */
public class AsyncCallBacker<T> implements ICallbacker<T> {
    private IUnbinderManager mUnbinderManager;
    private final boolean mRunLoading;

    public AsyncCallBacker(IUnbinderManager manager, boolean runLoading) {
        mUnbinderManager = manager;
        mRunLoading = runLoading;
    }


    @Override
    public void onBind(Disposable disposable) {
        mUnbinderManager.addUnbinder(disposable);
    }

    @Override
    public void onStart() {
        if (mRunLoading)
            mUnbinderManager.showLoadingView();

    }

    @Override
    public void onCallBack(T t) {

    }

    @Override
    public void onComplete() {
        mUnbinderManager.dismissLoadingView();
    }

    @Override
    public void onUnBind(Disposable disposable) {
        mUnbinderManager.removeUnbinder(disposable);
    }


    @Override
    public void onError(Throwable throwable) {
        mUnbinderManager.dismissLoadingView();
        BaseActivity activity = BaseApplication.getActivityManager().getLastResumeActivity();
        if (activity != null)
            activity.onException(throwable);
    }
}
