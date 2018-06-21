package com.zk.appframework.arouter;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.zk.java_utils.log.LogUtil;

import java.util.Random;

/**
 * author: ZK.
 * date:   On 2018/6/11.
 * description: 页面跳转的登录拦截器。跳转到目标页面若需要登录，会根据登录情况做相应处理
 */

@Interceptor(priority = 8)
public class LoginInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (postcard.getExtra() == ARouterExtras.NEED_LOGIN) {
            int a = new Random().nextInt(10);
            if (a > 5) {
                callback.onInterrupt(new Throwable("未登录"));
                LogUtil.d("未登录，跳转到登录界面");
            } else {
                callback.onContinue(postcard);
            }
        } else callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {

    }
}
