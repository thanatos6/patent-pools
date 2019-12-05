package com.suixingpay.util;

import lombok.Data;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/04
 *  用于校验拦截非法登录用户
 */
@Data
public class ExceptionUtil  extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public ExceptionUtil(String msg) {
        this.msg = msg;
    }
    public ExceptionUtil(String msg, Throwable e) {
        this.msg = msg;
    }
    public ExceptionUtil(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
    public ExceptionUtil(String msg, int code, Throwable e) {
        this.code = code;
    }

}
