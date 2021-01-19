package com.shan.yellowpages.base.exception.token;

import com.shan.yellowpages.base.exception.KhException;

/**
 * 非法 token 异常
 * @author hlmy
 */
public class KhTokenIllegalException extends KhException {

    private static final long serialVersionUID = 1L;

    public KhTokenIllegalException(int errorCode) {
        super(errorCode);
    }


    public KhTokenIllegalException(int errorCode, String message) {
        super(errorCode, message);
    }

    public KhTokenIllegalException(int errorCode, String errorMessage, String message) {
        super(errorCode, errorMessage, message);
    }


}
