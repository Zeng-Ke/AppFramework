package com.zk.android_lib.http.base;

import com.zk.android_utils.cache.DiskCacheHandler;
import com.zk.android_utils.cache.DisklruCacheProvider;
import com.zk.android_utils.cache.HttpCacheMode;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * author: ZK.
 * date:   On 2018-07-05.
 */
public class RxCache {


    private HttpCacheMode mCacheMode;
    private final RxCacheHandler mRxCacheHandler;

    public RxCache(HttpCacheMode cacheMode) {
        mCacheMode = cacheMode;
        mRxCacheHandler = new RxCacheHandler.Builder().setCacheHandler(new DiskCacheHandler(new DisklruCacheProvider("httpdata", 50, 2,
                DisklruCacheProvider.SIZEUNIT.MB))).build();
    }


    public <T> ObservableTransformer<T, T> excute(final String key, final Class<T> tClass) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                //if (mMODE.equals(HttpCacheMode.FIRST_CACHE)) {
                Observable<T> cacheObservable = mRxCacheHandler.doGet(key, tClass);
                Observable<T> httpObservable = upstream.map(new Function<T, T>() {
                    @Override
                    public T apply(T t) throws Exception {
                        mRxCacheHandler.doSave(key, t);
                        return t;
                    }
                });
                return cacheObservable.switchIfEmpty(httpObservable);
                //  }
                //return null;
            }
        };
    }


    public <T> ObservableTransformer<T, T> excute(final String key, final Type type) {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                //TODO 使用策略者模式实现缓存的策略
                //if (mMODE.equals(HttpCacheMode.FIRST_CACHE)) {
                Observable<T> cacheObservable = mRxCacheHandler.doGet(key, type);
                Observable<T> httpObservable = upstream.map(new Function<T, T>() {
                    @Override
                    public T apply(T t) throws Exception {
                        mRxCacheHandler.doSave(key, t);
                        return t;
                    }
                });
                return cacheObservable.switchIfEmpty(httpObservable);
                //  }
                //return null;
            }
        };


    }

   /* public <T> ObservableTransformer<CommonDataBean<DoubleListBean>, CommonDataBean<DoubleListBean>> excute(final String key, final Type
            type) {
        return new ObservableTransformer<CommonDataBean<DoubleListBean>, CommonDataBean<DoubleListBean>>() {
            @Override
            public ObservableSource<CommonDataBean<DoubleListBean>> apply(Observable<CommonDataBean<DoubleListBean>> upstream) {
                //if (mMODE.equals(HttpCacheMode.FIRST_CACHE)) {
                Observable<CommonDataBean<DoubleListBean>> cacheObservable = mRxCacheHandler.doGet(key, type);
                Observable<CommonDataBean<DoubleListBean>> httpObservable = upstream.map(new Function<CommonDataBean<DoubleListBean>,
                        CommonDataBean<DoubleListBean>>() {
                    @Override
                    public CommonDataBean<DoubleListBean> apply(CommonDataBean<DoubleListBean> t) throws Exception {
                        mRxCacheHandler.doSave(key, t);
                        return t;
                    }
                });
                return cacheObservable.switchIfEmpty(httpObservable);
                // return httpObservable.switchIfEmpty(cacheObservable);

                //  }

                //return null;
            }
        };


    }

*/
}
