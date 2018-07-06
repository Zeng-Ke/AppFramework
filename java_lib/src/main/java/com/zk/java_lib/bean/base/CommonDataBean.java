package com.zk.java_lib.bean.base;

import com.zk.java_lib.bean.DoubleListBean;
import com.zk.java_lib.bean.SingleListBean;

import java.io.Serializable;
import java.util.List;

public class CommonDataBean<T extends SingleListBean>  implements Serializable{
    public String v;
    public List<T> items;
}