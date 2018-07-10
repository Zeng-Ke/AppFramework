package com.zk.android_utils.http;


import com.zk.android_utils.cache.disk.DiskCacheHandler;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author: ZK.
 * date:   On 2018-07-05.
 */
public class RxCacheHandler {

    private Builder mBuilder;
    private final DiskCacheHandler mCacheHandler;
    public static final long NO_EXPIRED = DiskCacheHandler.NO_EXPIRED; //不会过期


    public RxCacheHandler(Builder builder) {
        mBuilder = builder;
        mCacheHandler = mBuilder.getCacheHandler();
    }

    public static class Builder {

        private DiskCacheHandler cacheHandler;


        public DiskCacheHandler getCacheHandler() {
            return cacheHandler;
        }

        public Builder setCacheHandler(DiskCacheHandler cacheHandler) {
            this.cacheHandler = cacheHandler;
            return this;
        }

        public RxCacheHandler build() {
            return new RxCacheHandler(this);
        }
    }

    public <T> void doSave(final String key, final T value) {
        mCacheHandler.doSave(key, value);

    }

    public <T> void doSave(final String key, final T value, long expiredTime, TimeUnit unit) {
        mCacheHandler.doSave(key, value, expiredTime, unit);

    }


    public <T> Observable<T> doGet(final String key, final Class<T> tClass) {
        return Observable
                .create(new ObservableOnSubscribe<T>() {
                    @Override
                    public void subscribe(ObservableEmitter<T> e) throws Exception {
                        e.onNext(mCacheHandler.doGet(key, tClass));
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
                        return Observable.empty();
                    }
                });
    }


    public <T> Observable<T> doGet(final String key, final Type type, Function<Throwable, ObservableSource<T>> errorFunction) {
        return Observable
                .create(new ObservableOnSubscribe<T>() {
                    @Override
                    public void subscribe(ObservableEmitter<T> e) throws Exception {
                        e.onNext((T) mCacheHandler.doGet(key, type));
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .onErrorResumeNext(errorFunction != null ? errorFunction : new Function<Throwable, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(Throwable throwable) throws Exception {
                        return Observable.empty();
                    }
                });
    }

    public <T> Observable<T> doGet(final String key, final Type type) {
        return doGet(key, type, null);
    }


}
