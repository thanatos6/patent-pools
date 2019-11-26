package com.suixingpay.controller;

import com.suixingpay.pojo.Files;
import com.suixingpay.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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
    public Map<String, Object> upload(@RequestParam("id") int filePatentId, @RequestParam("file") MultipartFile file) {
        return fileService.insert(file, filePatentId);
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
    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam("fileId") int fileId) {
        Map<String, Object> map = new HashMap<>();
        int info = fileService.update(fileId);
        if (info >= 0) {
            map.put("result", "删除成功");
        } else {
            map.put("result", "删除失败");
        }
        return map;
    }


    /**
     * @param fileId
     * @param response
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/download")
    public void selectPathByFileId(@Param("fileId") int fileId, HttpServletResponse response) throws UnsupportedEncodingException {
        //通过查询方法获取信息
        fileService.selectPathByFileId(fileId, response);
    }
}
