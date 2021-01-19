package com.shan.yellowpages.base.exception;

/**
 * 用户数值不足exception
 *
 * @author hlmy
 */
public class KhUserValueLackException extends KhException {

    private static final long serialVersionUID = 1L;

    public KhUserValueLackException(int errorCode) {
        super(errorCode);
    }


    public KhUserValueLackException(int errorCode, String message) {
        super(errorCode, message);
    }

    public KhUserValueLackException(int errorCode, String errorMessage, String message) {
        super(errorCode, errorMessage, message);
    }


}
