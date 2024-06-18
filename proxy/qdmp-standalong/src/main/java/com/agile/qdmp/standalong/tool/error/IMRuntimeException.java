package com.agile.qdmp.standalong.tool.error;

/**
 * WxJava专用的runtime exception.
 *
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 11:38
 */
public class IMRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 4881698471192264412L;

    public IMRuntimeException(Throwable e) {
        super(e);
    }

    public IMRuntimeException(String msg) {
        super(msg);
    }

    public IMRuntimeException(String msg, Throwable e) {
        super(msg, e);
    }
}
