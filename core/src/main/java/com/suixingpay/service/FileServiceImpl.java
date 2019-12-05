package com.suixingpay.service;

import com.suixingpay.mapper.FileMapper;
import com.suixingpay.pojo.Files;
import com.suixingpay.util.GetIp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * @author duansiyu
 * @date 2019-11-25
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);
    @Autowired
    FileMapper fileMapper;

    /**
     * @param file
     * @param filePatentId
     * @param httpServletRequest
     * @author duansiyu
     */
    @Override
    public Map<String, Object> insert(MultipartFile file, Integer filePatentId, HttpServletRequest httpServletRequest) {
        //用于装数据、状态码、信息并返回
        Map<String, Object> map = new HashMap<>();
        LOGGER.info("[接受的参数为{}和{}]", file, filePatentId);
        //判断传入的专利id是否为空
        if (filePatentId == null) {
            LOGGER.info("[上传失败，输入的专利号为空]");
            map.put("result", "不存在此专利，请重新选择");
            map.put("status", 0);
            return map;
        }

        //判断上传的文件是否是空文件
        if (file.isEmpty()) {
            LOGGER.info("[上传失败，文件为空文件文件]");
            map.put("result", "文件为空文件文件，请重新选择");
            map.put("status", 2);
            return map;
        }

        //判断此文件是否过大
        if (file.getSize() > 1024 * 1024 * 20) {
            LOGGER.info("[上传失败，上传的文件过大]");
            map.put("result", "上传的文件过大，请重新选择");
            map.put("status", 3);
            return map;
        }

        //获取的文件名
        String fileName = file.getOriginalFilename();

        //判断上传的文件是否符合标准
        if (fileName.endsWith(".exe")) {
            LOGGER.info("上传失败，文件类型不符:" + fileName);
            map.put("result", "上传的文件类型不符，请重新选择");
            map.put("status", 4);
            return map;
        }

        //获取到保存文件的路径
        String filePath = httpServletRequest.getServletContext().getRealPath("/");
        LOGGER.info("文件存储路径: " + filePath);
        File filed = new File(filePath + fileName);
        LOGGER.info("文件名: " + fileName);
        try {
            //上传文件
            file.transferTo(filed);
            //上传到数据库中
            Files files = new Files();
            files.setFileCreateTime(new Date());
            files.setFileName(fileName);
            files.setFilePatentId(filePatentId);
            //动态获取本机Ip
            String realIP = GetIp.getRealIP();
            LOGGER.info("[获取到得本机ip{}]", realIP);
            //文件保存的路径
            files.setFilePath("http://" + realIP + ":8080/" + fileName);
            LOGGER.info("数据库存储路径: " + files.getFilePath());
            files.setFileStatus(1);
            fileMapper.insert(files);
            //返回的状态码和结果
            map.put("result", "上传成功");
            map.put("status", 1);
            LOGGER.info("上传成功");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return map;
    }


    /**
     * 根据文件Id去删除单个文件
     *
     * @param fileId
     * @return
     */
    @Override
    public int update(Integer fileId) {
        LOGGER.info("接受的参数为[{}]", fileId);
        return fileMapper.update(fileId);
    }


    /**
     * 根据专利Id查询该专利下的所有文件
     *
     * @param filePatentId
     * @return
     */
    @Override
    public Map<String, Object> selectById(Integer filePatentId) {
        //用于装数据、状态码、信息并返回
        Map<String, Object> map = new HashMap<>();
        LOGGER.info("接受的参数为[{}]", filePatentId);
        //传值的判空
        if (filePatentId == null) {
            map.put("list", "没有此专利");
            map.put("status", "0");
        }
         if (list.size() == 0){
             LOGGER.info("该专利下无文件");
             map.put("status", "0");
             map.put("list", "");
         }else{
             LOGGER.info("查询成功");
             map.put("status", "1");
             map.put("list", list);
         }
             return map;
        List<Files> list = fileMapper.selectById(filePatentId);
        //判断该专利下是否有文件存在
        if (list.size() == 0) {
            LOGGER.info("此专利下没有文件");
            map.put("status", "0");
            map.put("list", "");
        } else {
            LOGGER.info("查询成功");
            map.put("status", "1");
            map.put("list", list);
        }
        return map;
    }


    /**
     * 根据文件Id下载当前文件
     *
     * @param fileId
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> selectPathByFileId(Integer fileId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        LOGGER.info("接受的参数为[{}]", fileId);
        //获取文件
        Files files = fileMapper.selectPathByFileId(fileId);
        String filePath = files.getFilePath();
        //判空操作
        if (fileId == null) {
            map.put("filePath", null);
            map.put("status", 0);
            LOGGER.info("[下载失败]");
        } else {
            map.put("filePath", filePath);
            map.put("status", 1);
            LOGGER.info("[下载成功,路径为:{}]", filePath);
        }
        return map;
    }
}