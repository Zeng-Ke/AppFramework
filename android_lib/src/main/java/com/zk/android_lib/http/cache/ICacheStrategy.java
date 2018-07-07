package com.zk.android_lib.http.cache;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableTransformer;

/**
 * author: ZK.
 * date:   On 2018-07-06.
 */
public interface ICacheStrategy {

    <T> ObservableTransformer<T, T> excute(String key, Type type, long time, TimeUnit unit);

    <T> ObservableTransformer<T, T> excute(String key, Type type);
}
