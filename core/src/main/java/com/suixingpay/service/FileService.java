package com.suixingpay.service;

import com.suixingpay.bean.Files;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    int insert(Files data);
    void  update (int fileId);
    List<Files> selectById (int filePatentId);
    Files selectPathByFileId(int fileId);
}