package com.zk.java_lib.exception;

/**
 * author: ZK.
 * date:   On 2018/6/23.
 */
public enum ErrorCode {

    NET_ERROR("444"),
    NOT_FOUND("404"),
    SERVER("505"),
    SUCCESS("0x00000000");


    private String mCode;


    ErrorCode(String code) {
        mCode = code;
    }

    public String getCode() {
        return mCode;
    }

    public static ErrorCode parse(String code) {
        ErrorCode errorCode = null;
        switch (code) {
            case "444":
                errorCode = NET_ERROR;
                break;
            case "404":
                errorCode = NET_ERROR;
                break;
            default:
                errorCode = SERVER;
                break;
        }
        return errorCode;

    }


}
