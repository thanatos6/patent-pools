package com.suixingpay.service;

import com.suixingpay.pojo.Files;

import java.util.List;

public interface FileService {
    int insert(Files data);
    void  update (int fileId);
    List<Files> selectById (int filePatentId);
    Files selectPathByFileId(int fileId);
}