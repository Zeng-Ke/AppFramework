package com.zk.android_utils.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.zk.android_utils.ToastUtils;
import com.zk.android_utils.manager.ActivityUtil;
import com.zk.java_lib.exception.NetworkException;
import com.zk.java_utils.log.LogUtil;

/**
 * author: ZK.
 * date:   On 2018/6/7.
 */
public class BaseActivity extends AppCompatActivity implements IView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(this.getClass().getName() + "  :    onCreate");
        ActivityUtil.add(this);

    }


    public void onException(Throwable throwable) {
        ToastUtils.showShort(this, throwable.getMessage());
    }


    @Override
    protected void onResume() {
        super.onResume();
        ActivityUtil.setLastResumeActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(this.getClass().getName() + "  :    onDestroy");
        ActivityUtil.removeActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            return onBeforeBackPress() ? true : super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }


    protected boolean onBeforeBackPress() {
        if (ActivityUtil.size() == 1) {
            moveTaskToBack(true);
            return true;
        } else
            return false;
    }

}
