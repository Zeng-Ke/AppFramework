package com.zk.java_lib.bean.base;

import com.zk.java_lib.bean.SingleListBean;

import java.util.List;

public class CommonDataBean<T extends SingleListBean> {
    public String v;
    public List<T> items;
}