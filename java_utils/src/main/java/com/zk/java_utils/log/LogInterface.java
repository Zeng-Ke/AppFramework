package com.zk.java_utils.log;

/**
 * author: ZK.
 * date:   On 2018/6/6.
 */
public interface LogInterface {

    void d(Object object);

    void d(String tag, Object object);

    void v(String msg);

    void v(String tag, String msg);

    void i(String msg);

    void i(String tag, String msg);

    void w(String msg);

    void w(String tag, String msg);

    void e(String msg);

    void e(String tag, Throwable throwable);

    void json(String json);

    void json(String tag, String json);

}
