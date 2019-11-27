package com.suixingpay.service;

import com.suixingpay.mapper.FileMapper;
import com.suixingpay.pojo.Files;
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
        //文件路径
        String filePath=request.getSession().getServletContext().getRealPath("/");
      //  String filePath = "D://test/";
        File filed = new File(filePath +  fileName );
        try {
            file.transferTo(filed);
            LOGGER.info("上传成功");
            //上传到数据库中
            Files files = new Files();
            files.setFileCreateTime(new Date());
            files.setFileName(fileName);
            files.setFilePatentId(filePatentId);
            files.setFilePath("http://172.16.42.126:8080/"+fileName);
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
    public String selectPathByFileId(int fileId, HttpServletRequest request) {
        //获取文件
        Files files =fileMapper.selectPathByFileId(fileId);
        String filePath = files.getFilePath();
//        if (fileName != null) {
//            //设置文件路径
//            File file = new File(filePath+fileName);
//            // 如果文件名存在，则进行下载
//            if (file.exists()) {
//                // 配置
//                response.setHeader("content-type", "application/octet-stream");
//                response.setContentType("application/octet-stream");
//                // 下载文件能正常显示中文
//                try {
//                    response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                // 文件下载
//                byte[] buffer = new byte[1024];
//                FileInputStream fis = null;
//                BufferedInputStream bis = null;
//
//                try {
//                    //io流输入
//                    fis = new FileInputStream(file);
//                    //保证文件的准确性
//                    bis = new BufferedInputStream(fis);
//                    //io流输出
//                    ServletOutputStream os = response.getOutputStream();
//                    int i = 0;
//                    while ((i = bis.read(buffer)) != -1) {
//                        os.write(buffer, 0, i);
//                        os.flush();
//                    }
//                    //刷新，否则写入的时候写不全
//                   LOGGER.info("下载成功");
//                }
//                catch (Exception e) {
//                    LOGGER.info("下载失败");
//                }
//                //关流
//                finally {
//                    if (bis != null) {
//                        try {
//                            bis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
         return filePath;
    }
}