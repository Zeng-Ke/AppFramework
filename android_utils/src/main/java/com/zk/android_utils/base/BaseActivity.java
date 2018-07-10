package com.zk.android_utils.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.zk.android_utils.utils.ToastUtils;
import com.zk.android_utils.executors.ThreadExecutor;
import com.zk.android_utils.fragments.LoadingFragment;
import com.zk.android_utils.manager.ActivityUtil;
import com.zk.java_utils.log.LogUtil;

/**
 * author: ZK.
 * date:   On 2018/6/7.
 */
public class BaseActivity extends AppCompatActivity implements IView {


    private LoadingFragment mLoadingDialog;
    private int mLoadingCount = 0; //当前页面显式加载数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(this.getClass().getName() + "  :    onCreate");
        ActivityUtil.add(this);

    }


    public void onException(final Throwable throwable) {
        ThreadExecutor.runInMain(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showShort( throwable.getMessage());
            }
        });
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

    @Override
    public void showLoadingView() {
        ThreadExecutor.runInMain(new Runnable() {
            @Override
            public void run() {
                synchronized (BaseActivity.this) {
                    if (mLoadingDialog == null)
                        mLoadingDialog = LoadingFragment.getInstance(getSupportFragmentManager());
                    mLoadingDialog.show();
                    mLoadingCount++;
                }
            }
        });
    }

    @Override
    public void dismissLoadingView() {
        if (mLoadingDialog != null) {
            ThreadExecutor.runInMain(new Runnable() {
                @Override
                public void run() {
                    synchronized (BaseActivity.this) {
                        mLoadingCount--;
                        if (mLoadingCount <= 0) {
                            mLoadingDialog.dismissAllowingStateLoss();
                            mLoadingDialog.onDetach();
                        }
                    }
                }
            });
        }
    }

}
