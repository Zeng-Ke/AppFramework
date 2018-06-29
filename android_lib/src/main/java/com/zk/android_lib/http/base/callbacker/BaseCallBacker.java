package com.zk.android_lib.http.base.callbacker;

import com.zk.java_lib.exception.NetworkException;
import com.zk.java_utils.log.LogUtil;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;

/**
 * author: ZK.
 * date:   On 2018/6/25.
 */
public class BaseCallBacker<T> extends DisposableObserver<T> {

    private ICallbacker<T> mOberver;

    public BaseCallBacker(ICallbacker<T> oberver) {
        mOberver = oberver;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mOberver.onBind(this);
        mOberver.onStart();
    }

    @Override
    public void onNext(T value) {
        mOberver.onCallBack(value);
    }

    @Override
    public void onError(Throwable throwable) {
        LogUtil.e(throwable.getClass().getName() + ":" + throwable.getMessage());
        if (throwable instanceof SocketException || throwable instanceof SocketTimeoutException || throwable instanceof
                UnknownHostException || throwable instanceof ConnectException)
            throwable = new NetworkException("网络错误");
        mOberver.onError(throwable);
        mOberver.onUnBind(this);
    }

    @Override
    public void onComplete() {
        LogUtil.d("onComplete");
        mOberver.onComplete();
        mOberver.onUnBind(this);
    }
}
