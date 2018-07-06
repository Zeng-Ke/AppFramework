package com.zk.appframework.activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zk.android_lib.presenters.MainPresenter;
import com.zk.appframework.Constants.RouterPaths;
import com.zk.appframework.R;
import com.zk.appframework.arouter.ARouterPresenterActivity;

@Route(path = RouterPaths.Main_Activity_Path)
public class MainActivity extends ARouterPresenterActivity<MainPresenter, MainPresenter.MainView> implements MainPresenter.MainView {


    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        if (!handleUriLaunch(getIntent()))
            ARouter.getInstance().build(RouterPaths.Homepage_Activity_Path).navigation();
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
                    .build(RouterPaths.Homepage_Activity_Path)
                    .withString(HomePageActivity.INTENT_URI, uriStr)
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
