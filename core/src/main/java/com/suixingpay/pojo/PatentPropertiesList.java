package com.suixingpay.pojo;

/**
 * @author kongjian
 */
public class PatentPropertiesList {
    private int patentId;
    private String propertiesTitle;
    private String code;
    private int currentStatus;
    private String statusName;
    private String applyDate;
    private String person;
    private String codingPerson;
    private String patentTitle;

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getPatentTitle() {
        return patentTitle;
    }

    public void setPatentTitle(String patentTitle) {
        this.patentTitle = patentTitle;
    }

    public int getPatentId() {
        return patentId;
    }

    public void setPatentId(int patentId) {
        this.patentId = patentId;
    }

    public String getPropertiesTitle() {
        return propertiesTitle;
    }

    public void setPropertiesTitle(String propertiesTitle) {
        this.propertiesTitle = propertiesTitle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getCodingPerson() {
        return codingPerson;
    }

    public void setCodingPerson(String codingPerson) {
        this.codingPerson = codingPerson;
    }
}
