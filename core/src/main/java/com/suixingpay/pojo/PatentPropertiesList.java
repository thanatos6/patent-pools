package com.suixingpay.pojo;

public class PatentPropertiesList {
    private int patentId;
    private String propertiesTitle;
    private String code;
    private int status;
    private String statusName;
    private String applyDate;
    private String person;
    private String codingPerson;
//    public PatentPropertiesList(Integer patentId, String propertiesTitle, String code, Integer status) {
//        this.patentId = patentId;
//        this.propertiesTitle = propertiesTitle;
//        this.code = code;
//        this.status = status;
//    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
