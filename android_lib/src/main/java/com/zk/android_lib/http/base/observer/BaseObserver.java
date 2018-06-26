package com.zk.android_lib.http.base.observer;

import android.util.Log;

import com.zk.java_lib.exception.NetworkException;
import com.zk.java_utils.log.LogUtil;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;

/**
 * author: ZK.
 * date:   On 2018/6/25.
 */
public class BaseObserver<T> extends DisposableObserver<T> {

    private IObserver<T> mOberver;

    public BaseObserver(IObserver<T> oberver) {
        mOberver = oberver;
    }

    @Override
    protected void onStart() {
        LogUtil.d("=====", "onStart");
        super.onStart();
        mOberver.onBind(this);
        mOberver.onStart();
    }

    @Override
    public void onNext(T value) {
        LogUtil.d("=====", "onCallBack");
        mOberver.onCallBack(value);
    }

    @Override
    public void onError(Throwable throwable) {
        LogUtil.d("=====", "onError");
        LogUtil.e(throwable.getMessage());
        if (throwable instanceof SocketException || throwable instanceof SocketTimeoutException || throwable instanceof
                UnknownHostException)
            throwable = new NetworkException("网络错误");
        mOberver.onError(throwable);
    }

    @Override
    public void onComplete() {
        LogUtil.d("=====", "onComplete");
        LogUtil.d("onComplete");
        mOberver.onComplete();
        mOberver.onUnBind(this);
    }
}
