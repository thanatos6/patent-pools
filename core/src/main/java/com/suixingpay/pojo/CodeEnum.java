package com.suixingpay.pojo;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/11/25 22:18
 */
public enum CodeEnum {
    SUCCESS("200", "成功"),
    WARNING("404", "失败"),
    FAIL("500", "服务器异常");



    private String code;
    private String msg;

    private CodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }}
