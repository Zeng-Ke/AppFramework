package com.zk.android_lib.http.base.transformer;

import com.zk.android_lib.http.base.interceptor.AsyncCallBackInterceptor;
import com.zk.android_lib.http.base.interceptor.ExceptionInterceptor;
import com.zk.android_lib.http.base.interceptor.HttpDataInterceptor;
import com.zk.java_lib.bean.base.BaseBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: ZK.
 * date:   On 2018-06-28.
 * description：整个过程在子线程操作(可定义拦截器，如果不需要，Io_Transformer)
 */
public class Io_WithInterceptTransformer<T, R> implements ObservableTransformer<BaseBean<T>, R> {

    private AsyncCallBackInterceptor<T, R> mInterceptor;

    public Io_WithInterceptTransformer(AsyncCallBackInterceptor<T, R> interceptor) {
        mInterceptor = interceptor;
    }

    @Override
    public ObservableSource<R> apply(Observable<BaseBean<T>> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .map(new ExceptionInterceptor<T>())
                .map(new HttpDataInterceptor<T>())
                .map(mInterceptor)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                });
    }
}
