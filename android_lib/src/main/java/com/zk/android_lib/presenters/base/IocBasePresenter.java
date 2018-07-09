package com.zk.android_lib.presenters.base;

import com.zk.android_lib.ioc.component.DaggerPresenterComponent;
import com.zk.android_lib.ioc.component.PresenterComponent;
import com.zk.android_utils.base.BasePresenter;
import com.zk.android_utils.base.IView;
import com.zk.android_utils.http.IUnbinderManager;

import java.util.LinkedList;

import io.reactivex.disposables.Disposable;

/**
 * author: ZK.
 * date:   On 2018-07-09.
 */
public abstract class IocBasePresenter<T extends IView> extends BasePresenter<T> implements IUnbinderManager {


    private volatile LinkedList<Disposable> unbinders = new LinkedList<>();

    private final PresenterComponent mComponent;

    public IocBasePresenter(T iView) {
        super(iView);
        mComponent = DaggerPresenterComponent.builder().build();
        componentInject(mComponent);
    }

    public abstract void componentInject(PresenterComponent component);


    @Override
    public void addUnbinder(Disposable disposable) {
        synchronized (this) {
            unbinders.add(disposable);
        }
    }

    @Override
    public void removeUnbinder(Disposable disposable) {
        synchronized (this) {
            unbinders.remove(disposable);
        }
    }

    @Override
    public void showLoadingView() {
        getView().showLoadingView();
    }

    @Override
    public void dismissLoadingView() {
        getView().dismissLoadingView();
    }


    @Override
    protected void onViewDestory() {
        synchronized (this) {
            for (Disposable unbinder : unbinders) {
                if (!unbinder.isDisposed())
                    unbinder.dispose();
            }
        }
        super.onViewDestory();
    }
}
