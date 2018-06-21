package com.zk.java_utils;

/**
 * author: ZK.
 * date:   On 2018/6/7.
 */
public class StringUtil {

    public static final String EMPTY = "";


    public static boolean equal(String str1, String str2) {
        return str1 != null && str2 != null && str1.equals(str2);
    }


    public static boolean isNullOrEmpty(String str) {
        return EMPTY.equals(str) || str == null;
    }

}
