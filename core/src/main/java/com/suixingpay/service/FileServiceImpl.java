package com.suixingpay.service;

import com.suixingpay.mapper.FileMapper;
import com.suixingpay.pojo.Files;
import com.suixingpay.util.GetIp;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author duansiyu
 *
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);
    @Autowired
    FileMapper fileMapper;

    @Override
    public Map<String,Object> insert(MultipartFile file,int filePatentId,HttpServletRequest request){
        LOGGER.info("[接受的参数为{}和{}]",file,filePatentId);
        Map<String,Object> map =new HashMap<>();
        if (file.isEmpty()) {
            LOGGER.info("[上传失败，请选择文件]");
            map.put("result","上传失败，请选择文件");
            return map;
        }
        //文件名
        String fileName = file.getOriginalFilename();
        //获取虚拟文件路径
        String filePath=request.getSession().getServletContext().getRealPath("/");
        File filed = new File(filePath +  fileName );
        try {
            file.transferTo(filed);
            LOGGER.info("上传成功");
            //上传到数据库中
            Files files = new Files();
            files.setFileCreateTime(new Date());
            files.setFileName(fileName);
            files.setFilePatentId(filePatentId);
            //动态获取本机Ip
            String realIP = GetIp.getRealIP();
            LOGGER.info("[获取到得本机ip{}]",realIP);
            //文件保存得路径
            files.setFilePath("http://"+realIP+":8080/"+fileName);
            LOGGER.info(files.getFilePath());
            LOGGER.info(files.getFileName());
            files.setFileStatus(1);
            fileMapper.insert(files);
            map.put("result","上传成功");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return map;
        }

    @Override
    public int update(int fileId) {
       return fileMapper.update(fileId);
    }

    @Override
    public Map<String, Object> selectById(int filePatentId) {
         Map<String, Object> map =new HashMap<>(0);
         List<Files> list =fileMapper.selectById(filePatentId);
            if (list.size() ==0){
                 map.put("status","0");
                 map.put("list",null);
                 return map;
              }
                 map.put("status","1");
                 map.put("list",list);
                 LOGGER.info("查询成功");
                 return map;
    }

    @Override
    public Map<String,Object> selectPathByFileId(int fileId, HttpServletRequest request) {
        Map<String,Object> map =new HashMap<>();
        //获取文件
        Files files =fileMapper.selectPathByFileId(fileId);
        String filePath = files.getFilePath();
        if (filePath==null){
            map.put("filePath",null);
            map.put("status",0);
            LOGGER.info("[下载失败]");
        }else {
            map.put("filePath",filePath);
            map.put("status",1);
            LOGGER.info("[下载成功,路径为:{}]",filePath);
        }
         return map;
    }
}