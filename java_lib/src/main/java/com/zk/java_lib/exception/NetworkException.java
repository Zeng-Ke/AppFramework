package com.zk.java_lib.exception;

/**
 * author: ZK.
 * date:   On 2018/6/23.
 */
public class NetworkException extends Exception {


    public NetworkException(String message) {
        super(message, new ApiException(ErrorCode.NET_ERROR, message));
    }


    public NetworkException(String message, Throwable cause) {
        super(message, new ApiException(ErrorCode.NET_ERROR, message, cause));
    }
}
