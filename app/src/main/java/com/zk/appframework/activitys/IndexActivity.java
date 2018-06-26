package com.zk.appframework.activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zk.appframework.R;
import com.zk.appframework.arouter.ARouterPresenterActivity;
import com.zk.appframework.presenters.IndexPresenter;
import com.zk.java_utils.StringUtils;

@Route(path = IndexActivity.ROUTER_PATH)
public class IndexActivity extends ARouterPresenterActivity<IndexPresenter, IndexPresenter.IndexView> implements IndexPresenter.IndexView {

    public static final String ROUTER_PATH = "/main/index";

    public static final String INTENT_URI = "intent_uri";
    private TextView mTvInfo;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_index);
        handleUriLaunch(getIntent());
        mTvInfo = findViewById(R.id.tv_text);
        getPresenter().getData();
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
    }
}
