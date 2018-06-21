package com.zk.appframework.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zk.appframework.R;
import com.zk.appframework.arouter.ARouterBaseActivity;
import com.zk.java_utils.log.LogUtil;

/**
 * author: ZK.
 * date:   On 2018/6/11.
 */
@Route(path = GuideActivity.ROUTER_PATH)
public class GuideActivity extends ARouterBaseActivity {

    @Autowired
    String name;
    @Autowired
    int number;


    public static final String ROUTER_PATH = "/main/guide";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ARouter.getInstance().inject(this);
        LogUtil.i(name);
    }


}
