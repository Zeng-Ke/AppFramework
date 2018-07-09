package com.zk.android_utils.http;

import com.zk.android_utils.cache.DiskCacheHandler;
import com.zk.android_utils.cache.DisklruCacheProvider;
import com.zk.android_utils.http.cache.HttpCacheMode;
import com.zk.android_utils.http.cache.ICacheStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * author: ZK.
 * date:   On 2018-07-05.
 */
public class RxCache {


    private final RxCacheHandler mRxCacheHandler;
    private Map<String, ICacheStrategy> cacheStrategyMap = new ConcurrentHashMap();

    public RxCache() {
        mRxCacheHandler = new RxCacheHandler.Builder().setCacheHandler(new DiskCacheHandler(new DisklruCacheProvider("httpdata", 50, 2,
                DisklruCacheProvider.SIZEUNIT.MB))).build();
    }



    public <T> ObservableTransformer<T, T> excute(HttpCacheMode cacheMode, final String key, final Class<T> tClass) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> cacheObservable = mRxCacheHandler.doGet(key, tClass);
                Observable<T> httpObservable = upstream.map(new Function<T, T>() {
                    @Override
                    public T apply(T t) throws Exception {
                        mRxCacheHandler.doSave(key, t);
                        return t;
                    }
                });
                return cacheObservable.switchIfEmpty(httpObservable);
            }
        };
    }


    public <T> ObservableTransformer<T, T> excute(HttpCacheMode cacheMode, final String key, final Type type) {
        return getCacheStrategy(cacheMode).excute(key, type);

    }

    public <T> ObservableTransformer<T, T> excute(HttpCacheMode cacheMode, final String key, final Type type, long time, TimeUnit unit) {
        return getCacheStrategy(cacheMode).excute(key, type, time, unit);
    }


    public ICacheStrategy getCacheStrategy(HttpCacheMode cacheMode) {

        ICacheStrategy cacheStrategy;
        String pakName = ICacheStrategy.class.getPackage().getName();
        try {
            Class<?> clz = Class.forName(pakName + "." + cacheMode.className);
            cacheStrategy = cacheStrategyMap.get(cacheMode.className);
            if (cacheStrategy == null) {
                Constructor constructor = clz.getConstructor(RxCacheHandler.class);
                cacheStrategy = (ICacheStrategy) constructor.newInstance(mRxCacheHandler);
                cacheStrategyMap.put(cacheMode.className, cacheStrategy);
            }
            return cacheStrategy;
        } catch (Exception e) {
            throw new RuntimeException("get ICacheStrategy error:  " + e.getMessage());
        }
    }


}
