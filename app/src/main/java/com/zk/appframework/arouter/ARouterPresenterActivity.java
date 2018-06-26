package com.zk.appframework.arouter;

import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.zk.android_utils.base.BasePresenter;
import com.zk.android_utils.base.IView;
import com.zk.android_utils.base.PresenterActivity;
import com.zk.java_utils.log.LogUtil;

/**
 * author: ZK.
 * date:   On 2018/6/11.
 */
public  class ARouterPresenterActivity<T extends BasePresenter<E>, E extends IView> extends PresenterActivity<T, E> {


    public NavCallback getNavCallBack() {
        return new FrameworkNavCallBack();
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }


    protected class FrameworkNavCallBack extends NavCallback {

        @Override
        public void onArrival(Postcard postcard) {
        }

        @Override
        public void onInterrupt(Postcard postcard) {
            //回调是在子线程，UI操作需切换回主线程
            switch (postcard.getExtra()) {
                case ARouterExtras.NEED_LOGIN:
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ARouterPresenterActivity.this, "跳转到登录界面", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;

            }
        }

        @Override
        public void onLost(Postcard postcard) {
            //回调是在子线程，UI操作需切换回主线程
            LogUtil.e("找不到页面");
        }

        @Override
        public void onFound(Postcard postcard) {

        }

    }


}
