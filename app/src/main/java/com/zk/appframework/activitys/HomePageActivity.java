package com.zk.appframework.activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zk.android_utils.ToastUtils;
import com.zk.appframework.R;
import com.zk.appframework.arouter.ARouterPresenterActivity;
import com.zk.android_lib.presenters.HomePageActivityPresenter;
import com.zk.java_utils.StringUtils;

@Route(path = HomePageActivity.ROUTER_PATH)
public class HomePageActivity extends ARouterPresenterActivity<HomePageActivityPresenter, HomePageActivityPresenter.IndexView> implements
        HomePageActivityPresenter.IndexView {

    public static final String ROUTER_PATH = "/main/index";

    public static final String INTENT_URI = "intent_uri";
    private TextView mTvInfo;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_index);
        handleUriLaunch(getIntent());
        mTvInfo = findViewById(R.id.tv_text);
        getPresenter().getData();
        getPresenter().getData();
        getPresenter().getData();
        getPresenter().getData();
        getPresenter().getData();

        mTvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(MainActivity.ROUTER_PATH).navigation();
                finish();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleUriLaunch(intent);
    }

    public void handleUriLaunch(Intent intent) {
        String uriStr = intent.getStringExtra(INTENT_URI);
        if (!StringUtils.isNullOrEmpty(uriStr)) {
            Uri uri = Uri.parse(uriStr);
            ARouter.getInstance().build(uri).navigation();
        }
    }



    @Override
    public void onGetDataSuccess(String info) {
        mTvInfo.setText(info);
        ToastUtils.showShort(this,"onGetDataSuccess");
       // Log.d("====","onGetDataSuccess");
    }
}
