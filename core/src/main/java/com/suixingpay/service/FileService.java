package com.suixingpay.service;

import com.suixingpay.pojo.CodeEnum;
import com.suixingpay.pojo.Files;
import com.suixingpay.pojo.Response;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
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