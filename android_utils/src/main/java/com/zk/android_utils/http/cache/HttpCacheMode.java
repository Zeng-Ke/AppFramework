package com.zk.android_utils.http.cache;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author: ZK.
 * date:   On 2018-07-04.
 */
public enum HttpCacheMode {


   /* public static final int NO_CACHE = 0x11; //不加载缓存
    public static final int FIRST_CACHE = 0x12; //先加载缓存，缓存没有再加载网络
    public static final int CACHE_HTTP = 0x13; //先加载缓存，再加载网络会回调两次
    public static final int FIRST_HTTP = 0x14; //先加载网络失败再加载缓存


    @IntDef({NO_CACHE, FIRST_CACHE, CACHE_HTTP, FIRST_HTTP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MODE {
    }*/


    FIRST_CACHE("FirstCacheStrategy"),//先加载缓存，缓存没有再加载网络
    FIRST_HTTP("FirstHttpStrategy"),//先加载网络失败再加载缓存
    CACHE_HTTP("CacheAndHttpStrategy");//先加载缓存，再加载网络会回调两次


    public String className;

    HttpCacheMode(String className) {
        this.className = className;
    }
}
