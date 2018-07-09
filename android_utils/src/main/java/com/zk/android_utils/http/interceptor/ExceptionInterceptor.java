package com.zk.android_utils.http.interceptor;

import com.zk.java_lib.bean.base.BaseBean;
import com.zk.java_lib.exception.ApiException;
import com.zk.java_lib.exception.ErrorCode;
import com.zk.java_lib.exception.NetworkException;

import io.reactivex.functions.Function;

/**
 * author: ZK.
 * date:   On 2018/6/23.
 */
public class ExceptionInterceptor<T> implements Function<BaseBean<T>, BaseBean<T>> {
    @Override
    public BaseBean apply(BaseBean baseBean) throws Exception {
        if (baseBean == null)
            throw new NetworkException("网络错误");
        if (!ErrorCode.SUCCESS.getCode().equals(baseBean.state_code))
            throw new ApiException(ErrorCode.parse(baseBean.state_code), baseBean.state_code);
        return baseBean;
    }
}
