package com.zk.android_lib.http.base.observer;

import com.zk.android_utils.base.BaseActivity;
import com.zk.android_utils.base.BaseApplication;

import io.reactivex.disposables.Disposable;

/**
 * author: ZK.
 * date:   On 2018/6/25.
 */
public class MOberver<T> implements IObserver<T> {


    @Override
    public void onSubscribe(Disposable disposable) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable throwable) {
        BaseActivity activity = BaseApplication.getActivityManager().getLastResumeActivity();
        if (activity != null)
            activity.onException(throwable);
    }
}
