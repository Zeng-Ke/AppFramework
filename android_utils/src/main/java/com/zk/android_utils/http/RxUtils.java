package com.zk.android_utils.http;

import com.zk.android_utils.http.interceptor.AsyncCallBackInterceptor;
import com.zk.android_utils.http.transformer.Io_MainTransformer;
import com.zk.android_utils.http.transformer.Io_MainWithInterceptTransformer;
import com.zk.android_utils.http.transformer.Io_Transformer;
import com.zk.android_utils.http.transformer.Io_WithInterceptTransformer;
import com.zk.java_lib.bean.base.BaseBean;

import io.reactivex.ObservableTransformer;

/**
 * author: ZK.
 * date:   On 2018/6/23.
 */
public class RxUtils {


    public static <T> ObservableTransformer<BaseBean<T>, T> io_main(ObservableTransformer<T, T> cacheTransformer) {
        return new Io_MainTransformer(cacheTransformer);
    }

    public static <T> ObservableTransformer<BaseBean<T>, T> io_main() {
        return new Io_MainTransformer(null);
    }

    public static <T, R> ObservableTransformer<BaseBean<T>, R> io_main(AsyncCallBackInterceptor<T, R> function) {
        return new Io_MainWithInterceptTransformer<>(null, function);
    }

    public static <T, R> ObservableTransformer<BaseBean<T>, R> io_main(ObservableTransformer<T,
            T> cacheTransformer, AsyncCallBackInterceptor<T, R> function) {
        return new Io_MainWithInterceptTransformer<>(cacheTransformer, function);
    }

    public static <T> ObservableTransformer<BaseBean<T>, T> io() {
        return new Io_Transformer<>();
    }

    public static <T, R> ObservableTransformer<BaseBean<T>, R> io(AsyncCallBackInterceptor<T, R> function) {
        return new Io_WithInterceptTransformer<>(function);
    }

}
