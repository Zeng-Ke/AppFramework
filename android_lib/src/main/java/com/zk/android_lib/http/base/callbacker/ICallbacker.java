package com.zk.android_lib.http.base.callbacker;

import io.reactivex.disposables.Disposable;

/**
 * author: ZK.
 * date:   On 2018/6/25.
 */
public interface ICallbacker<T> {

    void onBind(Disposable disposable);

    void onStart();

    void onCallBack(T t);

    void onComplete();

    void onUnBind(Disposable disposable);

    void onError(Throwable throwable);
}
