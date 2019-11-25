package com.suixingpay.service;

import com.suixingpay.pojo.Files;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author duansiyu
 */
public interface FileService {
    String insert(MultipartFile data,int filePatentId);
    void  update (int fileId);
    Map<String, Object> selectById (int filePatentId);
    Files selectPathByFileId(int fileId, HttpServletResponse response);
}