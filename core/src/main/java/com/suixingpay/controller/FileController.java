package com.suixingpay.controller;

import com.suixingpay.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @author duansiyu
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    /**
     * 上传文件
     *
     * @param filePatentId
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Map<String, Object> upload(@RequestParam("id") int filePatentId, @RequestParam("file") MultipartFile file,HttpServletRequest request) {
        return fileService.insert(file, filePatentId,request );
    }


    /**
     * @param patentId
     * @return
     */
    @GetMapping("/select")
    public Map<String, Object> selectById(@RequestParam("id") int patentId) {
        return fileService.selectById(patentId);
    }


    /**
     * @param fileId
     * @return
     */
    @GetMapping("/update")
    public Map<String, Object> update(@RequestParam("fileId") Integer fileId) {
        Map<String, Object> map = new HashMap<>();
        if (null == fileId) {
            map.put("result", "参数为空");
            return map;
        }
        int info = fileService.update(fileId);
        if (info >= 1) {
            map.put("result", "删除成功");
            LOGGER.info("删除成功");
        } else {
            map.put("result", "删除失败");
            LOGGER.info("删除失败");
        }
        return map;
    }


    /**
     * @param fileId
     * @param request
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/download")
    public Map<String,Object> selectPathByFileId(@Param("fileId") Integer fileId, HttpServletRequest request) throws UnsupportedEncodingException {

        return fileService.selectPathByFileId(fileId, request);
    }
}