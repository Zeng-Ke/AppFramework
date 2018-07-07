package com.zk.java_lib.exception;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * author: ZK.
 * date:   On 2018/6/23.
 */
public class NetworkException extends Exception {


    public static  boolean isNetWorkException(Throwable throwable) {
        return throwable instanceof SocketException || throwable instanceof SocketTimeoutException || throwable instanceof
                UnknownHostException || throwable instanceof ConnectException;

    }


    public NetworkException(String message) {
        super(message, new ApiException(ErrorCode.NET_ERROR, message));
    }


    public NetworkException(String message, Throwable cause) {
        super(message, new ApiException(ErrorCode.NET_ERROR, message, cause));
    }
}
