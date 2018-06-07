package com.zk.android_utils.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zk.android_utils.PresenterUtil;

/**
 * author: ZK.
 * date:   On 2018/6/7.
 */
public class PresenterActivity<T extends BasePresenter<E>, E extends IView> extends BaseActivity implements IView {


    private T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = PresenterUtil.createPresenter(this);
        mPresenter.onViewCreate();
    }

    protected T getPresenter() {
        return mPresenter;
    }


    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onViewStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onViewResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onViewPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.onViewResume();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onViewStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onViewDestory();
    }
}
