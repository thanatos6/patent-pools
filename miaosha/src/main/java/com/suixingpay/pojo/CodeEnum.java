package com.suixingpay.pojo;


/**
 * @author kongjian
 */

public enum CodeEnum {
    SUCCESS("0", "成功"),
    FAIL("-1", "服务器异常");



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
