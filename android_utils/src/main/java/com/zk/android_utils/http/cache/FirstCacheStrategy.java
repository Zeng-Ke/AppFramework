package com.zk.android_utils.http.cache;



import com.zk.android_utils.http.RxCacheHandler;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * author: ZK.
 * date:   On 2018-07-06.
 * description: 先加载缓存，没有缓存再加载网络
 */
public class FirstCacheStrategy implements ICacheStrategy {

    private RxCacheHandler mRxCacheHandler;


    public FirstCacheStrategy(RxCacheHandler rxCacheHandler) {
        if (rxCacheHandler == null)
            throw new NullPointerException("RxCacheHandler can not null");
        mRxCacheHandler = rxCacheHandler;
    }

    @Override
    public <T> ObservableTransformer<T, T> excute(final String key, final Type type, final long time, final TimeUnit unit) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> cacheObservable = mRxCacheHandler.doGet(key, type);
                Observable<T> httpObservable = upstream.map(new Function<T, T>() {
                    @Override
                    public T apply(T t) throws Exception {
                        if (time < 0)
                            mRxCacheHandler.doSave(key, t);
                        else
                            mRxCacheHandler.doSave(key, t, time, unit);
                        return t;
                    }
                });
                return cacheObservable.switchIfEmpty(httpObservable);
            }
        };
    }

    @Override
    public <T> ObservableTransformer<T, T> excute(String key, Type type) {
        return excute(key, type, RxCacheHandler.NO_EXPIRED, null);
    }
}
