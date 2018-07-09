package com.zk.android_utils.http.cache;


import com.zk.android_utils.http.RxCacheHandler;
import com.zk.java_lib.exception.NetworkException;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * author: ZK.
 * date:   On 2018-07-06.
 */
public class FirstHttpStrategy implements ICacheStrategy {

    private RxCacheHandler mRxCacheHandler;


    public FirstHttpStrategy(RxCacheHandler rxCacheHandler) {
        if (rxCacheHandler == null)
            throw new NullPointerException("RxCacheHandler can not null");
        mRxCacheHandler = rxCacheHandler;
    }


    @Override
    public <T> ObservableTransformer<T, T> excute(final String key, final Type type, final long time, final TimeUnit unit) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .map(new Function<T, T>() {
                            @Override
                            public T apply(T t) throws Exception {
                                if (time < 0)
                                    mRxCacheHandler.doSave(key, t);
                                else
                                    mRxCacheHandler.doSave(key, t, time, unit);
                                return t;
                            }
                        })
                        .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
                            @Override
                            public ObservableSource<? extends T> apply(final Throwable throwable) throws Exception {
                                return NetworkException.isNetWorkException(throwable) ? (ObservableSource<T>) getCacheObservable(key,
                                        type, throwable) :
                                        Observable
                                                .create(new ObservableOnSubscribe<T>() {
                                                    @Override
                                                    public void subscribe(ObservableEmitter<T> e) throws Exception {
                                                        e.onError(throwable);
                                                    }
                                                });
                            }
                        });
            }
        };
    }


    @Override
    public <T> ObservableTransformer<T, T> excute(String key, Type type) {
        return excute(key, type, RxCacheHandler.NO_EXPIRED, null);
    }


    private <T> Observable<T> getCacheObservable(final String key, final Type type, final Throwable throwable) {

        return mRxCacheHandler.doGet(key, type, new Function<Throwable, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(final Throwable throwable1) throws Exception {
                return Observable
                        .create(new ObservableOnSubscribe<T>() {
                            @Override
                            public void subscribe(ObservableEmitter<T> e) throws Exception {
                                e.onError(throwable);
                            }
                        });
            }
        });
    }
}
