package com.zk.appframework;

import android.os.Bundle;

import com.zk.android_utils.base.PresenterActivity;
import com.zk.java_utils.log.LogUtil;

public class MainActivity extends PresenterActivity<MainPresenter, MainPresenter.MainView> implements MainPresenter.MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPresenter().loadData();

    }


    @Override
    public void returnData(String s) {
        LogUtil.d(s);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
