package com.zk.appframework.activitys;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zk.android_utils.utils.AppUtils;
import com.zk.android_utils.utils.DeviceUtils;
import com.zk.android_utils.utils.ToastUtils;
import com.zk.appframework.Constants.RouterPaths;
import com.zk.appframework.R;
import com.zk.appframework.arouter.ARouterPresenterActivity;
import com.zk.android_lib.presenters.HomePageActivityPresenter;
import com.zk.java_utils.StringUtils;
import com.zk.java_utils.log.LogUtil;

import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;

@Route(path = RouterPaths.Homepage_Activity_Path)
public class HomePageActivity extends ARouterPresenterActivity<HomePageActivityPresenter, HomePageActivityPresenter.IndexView> implements
        HomePageActivityPresenter.IndexView {


    public static final String INTENT_URI = "intent_uri";
    private TextView mTvInfo;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_index);
        handleUriLaunch(getIntent());
        mTvInfo = findViewById(R.id.tv_text);
        getPresenter().getData1();
        //getPresenter().getJob();

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean)
                            ToastUtils.showShort("申请拍照权限成功");
                    }
                });


        mTvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouterPaths.Main_Activity_Path).navigation();
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
       /* LoadingFragment fragment = new LoadingFragment();
        fragment.show(getSupportFragmentManager());*/
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleUriLaunch(intent);
    }

    public void handleUriLaunch(Intent intent) {
        String uriStr = intent.getStringExtra(INTENT_URI);
        if (!StringUtils.isEmpty(uriStr)) {
            Uri uri = Uri.parse(uriStr);
            ARouter.getInstance().build(uri).navigation();
        }
    }


    @Override
    public void onGetDataSuccess(String info) {
        mTvInfo.setText(info);
        ToastUtils.showShort("onGetDataSuccess");
        // Log.d("====","onGetDataSuccess");
    }
}
