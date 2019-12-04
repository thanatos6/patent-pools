package com.suixingpay.pojo;

import java.util.Date;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/3 11:48
 */
public class RejectContent {
    //驳回原因表ID
    private Integer id;
    //专利ID
    private Integer patentId;
    //驳回原因
    private String rejectContent;
    //修改日期
    private Date modifyDate;
    //创建日期
    private Date createDate;
    //是否失效
    private Integer isDelete;
    //驳回人
    private Integer rejectUserId;
    //User实体
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RejectContent() {
    }

    public Integer getRejectUserId() {
        return rejectUserId;
    }

    public void setRejectUserId(Integer rejectUserId) {
        this.rejectUserId = rejectUserId;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPatentId() {
        return patentId;
    }

    public String getRejectContent() {
        return rejectContent;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPatentId(Integer patentId) {
        this.patentId = patentId;
    }

    public void setRejectContent(String rejectContent) {
        this.rejectContent = rejectContent;
    }
}
