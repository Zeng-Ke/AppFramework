package com.zk.appframework.arouter;

import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.zk.android_utils.base.BaseActivity;
import com.zk.java_utils.log.LogUtil;

/**
 * author: ZK.
 * date:   On 2018/6/11.
 */
public abstract class ARouterBaseActivity extends BaseActivity {


    public NavCallback getNavCallBack() {
        return new FrameworkNavCallBack();
    }


    class FrameworkNavCallBack extends NavCallback {


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
                            Toast.makeText(ARouterBaseActivity.this, "跳转到登录界面", Toast.LENGTH_SHORT).show();
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
