package com.zk.android_lib.http.base.transformer;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * author: ZK.
 * date:   On 2018-06-28.
 */
public abstract class RequestTransformer<T, R> implements Function<T, ObservableSource<R>> {

    @Override
    public ObservableSource<R> apply(T t) throws Exception {
        return transform(t);
    }

    public abstract ObservableSource<R> transform(T t) throws Exception;


}
