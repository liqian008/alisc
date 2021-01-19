package com.shan.yellowpages.base.exception;

/**
 * 缓存不存在的异常
 *
 * @author lq
 * @date 2013-9-22 下午04:28:22
 */
public class KhCacheNotExistException extends Exception {

    private static final long serialVersionUID = 1L;

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
