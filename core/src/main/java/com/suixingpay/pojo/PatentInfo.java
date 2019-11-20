package com.suixingpay.pojo;

import java.util.Date;

/**
 * 数据库的专利信息表的 pojo 映射
 * @author 詹文良
 * @time 2019-11-20
 */

public class PatentInfo {
    private Integer id;

    private String batch;

    private String caseNumber;

    private String applyNumber;

    private Date applyDate;

    private String applyTechLinkman;

    private String applyManChs;

    private String inventionChs;

    private String lawStatus;

    private String patentType;

    private String inventManChs;

    private String patentComment;

    private Date modifyDate;

    private Date createDate;

    private Byte isDelete;

    private Byte currentStatus;

    private String currentProcess;

    private Integer ownerUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber == null ? null : caseNumber.trim();
    }

    public String getApplyNumber() {
        return applyNumber;
    }

    public void setApplyNumber(String applyNumber) {
        this.applyNumber = applyNumber == null ? null : applyNumber.trim();
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplyTechLinkman() {
        return applyTechLinkman;
    }

    public void setApplyTechLinkman(String applyTechLinkman) {
        this.applyTechLinkman = applyTechLinkman == null ? null : applyTechLinkman.trim();
    }

    public String getApplyManChs() {
        return applyManChs;
    }

    public void setApplyManChs(String applyManChs) {
        this.applyManChs = applyManChs == null ? null : applyManChs.trim();
    }

    public String getInventionChs() {
        return inventionChs;
    }

    public void setInventionChs(String inventionChs) {
        this.inventionChs = inventionChs == null ? null : inventionChs.trim();
    }

    public String getLawStatus() {
        return lawStatus;
    }

    public void setLawStatus(String lawStatus) {
        this.lawStatus = lawStatus == null ? null : lawStatus.trim();
    }

    public String getPatentType() {
        return patentType;
    }

    public void setPatentType(String patentType) {
        this.patentType = patentType == null ? null : patentType.trim();
    }

    public String getInventManChs() {
        return inventManChs;
    }

    public void setInventManChs(String inventManChs) {
        this.inventManChs = inventManChs == null ? null : inventManChs.trim();
    }

    public String getPatentComment() {
        return patentComment;
    }

    public void setPatentComment(String patentComment) {
        this.patentComment = patentComment == null ? null : patentComment.trim();
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

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Byte getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Byte currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getCurrentProcess() {
        return currentProcess;
    }

    public void setCurrentProcess(String currentProcess) {
        this.currentProcess = currentProcess == null ? null : currentProcess.trim();
    }

    public Integer getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Integer ownerUserId) {
        this.ownerUserId = ownerUserId;
    }
}