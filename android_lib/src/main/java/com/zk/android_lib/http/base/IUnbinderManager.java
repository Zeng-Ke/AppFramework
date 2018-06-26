package com.zk.android_lib.http.base;

import io.reactivex.disposables.Disposable;

/**
 * author: ZK.
 * date:   On 2018-06-26.
 */
public interface IUnbinderManager {

    void addUnbinder(Disposable disposable);

    void removeUnbinder(Disposable disposable);

}
