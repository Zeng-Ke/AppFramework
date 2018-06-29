package com.zk.android_lib.http.base.transformer;

import com.zk.android_lib.http.base.interceptor.ExceptionInterceptor;
import com.zk.android_lib.http.base.interceptor.HttpDataInterceptor;
import com.zk.java_lib.bean.base.BaseBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: ZK.
 * date:   On 2018/6/23.
 *  description :IO线程耗时操作，完成回到主线程
 */
public class Io_MainTransformer<T> implements ObservableTransformer<BaseBean<T>, T> {


    @Override
    public ObservableSource<T> apply(Observable<BaseBean<T>> upstream) {

        return upstream
                .subscribeOn(Schedulers.io())
                .map(new ExceptionInterceptor<T>())
                .map(new HttpDataInterceptor<T>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                });
    }




}