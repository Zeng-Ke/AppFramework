package com.zk.android_lib.http.base;

import com.zk.android_lib.http.base.callbacker.BaseCallBacker;
import com.zk.android_lib.http.base.callbacker.ICallbacker;

/**
 * author: ZK.
 * date:   On 2018-06-26.
 */
public class BaseService {

    public <T> BaseCallBacker<T> getCallBacker(ICallbacker<T> observer) {
        return new BaseCallBacker<>(observer);
    }
}
