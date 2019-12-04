package com.suixingpay.pojo;

public class StatusCode {
    //状态码ID
    private Integer id;
    //当前状态
    private String processStatus;
    //权限角色
    private String role;
    //状态码
    private String processCode;

    public StatusCode() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }
}
