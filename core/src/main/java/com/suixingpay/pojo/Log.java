package com.suixingpay.pojo;

import java.util.Date;

public class Log {
    private Integer id;

    private Integer userId;

    private String message;

    private Integer patentInfoId;

    private Date createDate;

    private Date modifyDate;

    private Byte isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getPatentInfoId() {
        return patentInfoId;
    }

    public void setPatentInfoId(Integer patentInfoId) {
        this.patentInfoId = patentInfoId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                ", patentInfoId=" + patentInfoId +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", isDelete=" + isDelete +
                '}';
    }
}