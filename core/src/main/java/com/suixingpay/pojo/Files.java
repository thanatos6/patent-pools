package com.suixingpay.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 数据库的文件信息的 pojo 映射
 * @author 段思宇
 * @time 2019-11-20
 */
public class Files {
    private Integer fileId;

    private Integer filePatentId;

    private String filePath;

    private String fileName;

    private Integer fileStatus;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date fileCreateTime;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getFilePatentId() {
        return filePatentId;
    }

    public void setFilePatentId(Integer filePatentId) {
        this.filePatentId = filePatentId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Integer getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(Integer fileStatus) {
        this.fileStatus = fileStatus;
    }

    public Date getFileCreateTime() {
        return fileCreateTime;
    }

    public void setFileCreateTime(Date fileCreateTime) {
        this.fileCreateTime = fileCreateTime;
    }
}