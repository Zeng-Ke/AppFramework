package com.zk.android_lib.http.base;

import com.zk.android_lib.http.base.observer.BaseObserver;
import com.zk.android_lib.http.base.observer.IObserver;

/**
 * author: ZK.
 * date:   On 2018-06-26.
 */
public class BaseService {

    public <T> BaseObserver<T> getObserver(IObserver<T> observer) {
        return new BaseObserver<>(observer);
    }
}
