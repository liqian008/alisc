package com.shan.yellowpages.base.exception.token;

import com.shan.yellowpages.base.exception.KhException;

/**
 * token 过期异常
 * @author hlmy
 */
public class KhTokenExpireException extends KhException {

    private static final long serialVersionUID = 1L;

    public KhTokenExpireException(int errorCode) {
        super(errorCode);
    }


    public KhTokenExpireException(int errorCode, String message) {
        super(errorCode, message);
    }

    public KhTokenExpireException(int errorCode, String errorMessage, String message) {
        super(errorCode, errorMessage, message);
    }

}
