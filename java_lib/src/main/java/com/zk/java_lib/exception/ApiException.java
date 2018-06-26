package com.zk.java_lib.exception;

/**
 * author: ZK.
 * date:   On 2018/6/23.
 */
public class ApiException extends Exception {

    private ErrorCode mErrorCode;

    public ApiException(ErrorCode errorCode, String message) {
        super(message);
        mErrorCode = errorCode;
    }

    public ApiException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        mErrorCode = errorCode;
    }
}
