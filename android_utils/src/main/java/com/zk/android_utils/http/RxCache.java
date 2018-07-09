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


    private static RxCacheHandler mRxCacheHandler = null;
    private static Map<String, ICacheStrategy> cacheStrategyMap = new ConcurrentHashMap();

    private RxCache() {
    }

    public static void init(String uniqueName, long maxsize, int valuecount, DisklruCacheProvider.SIZEUNIT sizeunit) {
        mRxCacheHandler = new RxCacheHandler.Builder().setCacheHandler(new DiskCacheHandler(new DisklruCacheProvider(uniqueName, maxsize,
                valuecount, sizeunit))).build();
    }


    public static <T> ObservableTransformer<T, T> excute(HttpCacheMode cacheMode, final String key, final Class<T> tClass) {
        if (mRxCacheHandler == null)
            throw new NullPointerException("you must call RxCache.init() in Application'oncreate()");

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


    public static <T> ObservableTransformer<T, T> excute(HttpCacheMode cacheMode, final String key, final Type type) {
        return getCacheStrategy(cacheMode).excute(key, type);

    }

    public static <T> ObservableTransformer<T, T> excute(HttpCacheMode cacheMode, final String key, final Type type, long time, TimeUnit
            unit) {
        return getCacheStrategy(cacheMode).excute(key, type, time, unit);
    }


    public static ICacheStrategy getCacheStrategy(HttpCacheMode cacheMode) {
        if (mRxCacheHandler == null)
            throw new NullPointerException("you must call RxCache.init() in Application'oncreate()");

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
