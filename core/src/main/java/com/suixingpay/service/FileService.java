package com.suixingpay.service;

import com.suixingpay.pojo.Files;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author duansiyu
 */
public interface FileService {
    Map<String,Object> insert(MultipartFile data,int filePatentId);
    int  update (int fileId);
    Map<String, Object> selectById (int filePatentId);
    Files selectPathByFileId(int fileId, HttpServletResponse response);
}