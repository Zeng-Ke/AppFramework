package com.zk.android_lib.http.base.observer;

import io.reactivex.disposables.Disposable;

/**
 * author: ZK.
 * date:   On 2018/6/25.
 */
public interface IObserver<T> {

    public void onSubscribe(Disposable disposable);

    public void onNext(T t);


    public void onComplete();

    public void onError(Throwable throwable);
}
