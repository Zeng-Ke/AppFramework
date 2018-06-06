package com.zk.java_utils;

import com.zk.java_utils.log.LogUtil;

/**
 * author: ZK.
 * date:   On 2018/6/6.
 */
public class Test {

    public static void log(){
       /* LogUtil.d("======","haha");
        LogUtil.d("哈哈");*/
        LogUtil.e("haha",new NullPointerException("空指针"));
    }

}
