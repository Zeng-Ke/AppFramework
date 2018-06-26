package com.zk.android_lib.http.base;

import com.zk.java_lib.bean.base.BaseBean;

import io.reactivex.ObservableTransformer;

/**
 * author: ZK.
 * date:   On 2018/6/23.
 */
public class RxUtils {


    public static <T> ObservableTransformer<BaseBean<T>, T> io_main(){
        return new Io_MainTransformer();
    }


}
