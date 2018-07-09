package com.zk.android_utils.http.interceptor;

import com.zk.java_lib.bean.base.BaseBean;

import io.reactivex.functions.Function;

/**
 * author: ZK.
 * date:   On 2018/6/23.
 */
public class HttpDataInterceptor<T> implements Function<BaseBean<T>, T> {


    @Override
    public T apply(BaseBean<T> tBaseBean) throws Exception {
        return tBaseBean.data;
    }


}
