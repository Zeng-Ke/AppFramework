package com.zk.appframework.activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zk.appframework.presenters.MainPresenter;
import com.zk.appframework.R;
import com.zk.appframework.arouter.ARouterPresenterActivity;

@Route(path = MainActivity.ROUTER_PATH)
public class MainActivity extends ARouterPresenterActivity<MainPresenter, MainPresenter.MainView> implements MainPresenter.MainView {

    public static final String ROUTER_PATH = "/main/guide";

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        if (!handleUriLaunch(getIntent()))
            ARouter.getInstance().build(IndexActivity.ROUTER_PATH).navigation();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleUriLaunch(intent);
    }

    public boolean handleUriLaunch(Intent intent) {
        Uri uri = intent.getData();
        if (uri != null) {
            String uriStr = uri.toString();
            ARouter.getInstance()
                    .build(IndexActivity.ROUTER_PATH)
                    .withString(IndexActivity.INTENT_URI, uriStr)
                    .navigation(this, getNavCallBack());
            return true;
        }
        return false;
    }

    @Override
    protected boolean onBeforeBackPress() {
        moveTaskToBack(true);
        return true;
    }


}
