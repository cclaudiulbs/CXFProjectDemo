package com.cxf.demo.exception;

/**
 * @author cclaudiu
 */
public class MarshallerException extends RuntimeException {
    public MarshallerException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
