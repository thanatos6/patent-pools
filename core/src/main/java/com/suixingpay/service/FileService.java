package com.suixingpay.service;

import com.suixingpay.pojo.Files;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author duansiyu
 */
public interface FileService {
    Map<String,Object> insert(MultipartFile data, int filePatentId, HttpServletRequest httpServletRequest);
    int  update (int fileId);
    Map<String, Object> selectById (int filePatentId);
    Map<String,Object> selectPathByFileId(int fileId, HttpServletRequest request);
}