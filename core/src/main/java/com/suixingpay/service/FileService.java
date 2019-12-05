package com.suixingpay.service;

import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author duansiyu
 */
public interface FileService {

    Map<String, Object> insert(MultipartFile data, Integer filePatentId, HttpServletRequest httpServletRequest);

    int update(Integer fileId);

    Map<String, Object> selectById(Integer filePatentId);

    Map<String, Object> selectPathByFileId(Integer fileId, HttpServletRequest request);
}