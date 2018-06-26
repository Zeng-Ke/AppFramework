package com.zk.java_lib.bean;

/**
 * author: ZK.
 * date:   On 2018/5/11.
 */
public class PhoneInfoBean {


    /**
     * id : 13610173029
     * zipCode : 510000
     * cardType : 移动神州行卡
     * areaCode : 020
     * location : 广东 广州市
     */

    public String id;
    public String zipCode;
    public String cardType;
    public String areaCode;
    public String location;

    @Override
    public String toString() {
        return String.format("%1$s %2$s", location, cardType);
    }
}