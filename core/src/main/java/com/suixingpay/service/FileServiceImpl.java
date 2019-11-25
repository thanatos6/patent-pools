package com.suixingpay.service;

import com.suixingpay.mapper.FileMapper;
import com.suixingpay.pojo.Files;
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
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;

    @Override
    public String insert(MultipartFile file,int filePatentId){
        if (file.isEmpty()) {
            return "{\"result\": \"上传失败，请选择文件\"}";
        }
        //文件名
        String fileName = file.getOriginalFilename();
        //文件路径
        String filePath = "D://test/";
        UUID uuid=UUID.randomUUID();
        File filed = new File(filePath + uuid + fileName );
        try {
            file.transferTo(filed);
            //上传到数据库中
            Files files = new Files();
            files.setFileCreateTime(new Date());
            files.setFileName(fileName);
            files.setFilePatentId(filePatentId);
            files.setFilePath(filePath + uuid);
            files.setFileStatus(1);
            fileMapper.insert(files); ;
            return "{\"result\": \"上传成功\"}";
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return "{\"result\": \"上传失败\"}";
        }

    @Override
    public void update(int fileId) {
        fileMapper.update(fileId);
    }

    @Override
    public Map<String, Object> selectById(int filePatentId) {
         Map<String, Object> map =new HashMap<>(0);
         List<Files> files1 =fileMapper.selectById(filePatentId);
            if (files1.size() ==0){
                 map.put("status","500");
                 map.put("files1",null);
              }
                 map.put("status","200");
                 map.put("files1",files1);
                   return map;
    }

    @Override
    public Files selectPathByFileId(int fileId, HttpServletResponse response) {
        //获取文件
        Files files =fileMapper.selectPathByFileId(fileId);
        String fileName =files.getFileName();
        /*获取文件地址
        地址格式为：
        正规路径加UUID
        例：D://test/419dfa74-f1a2-4694-87f8-c5616b8673c3hello.txt
         */
        String filePath = files.getFilePath();
        if (fileName != null) {
            //设置文件路径
            File file = new File(filePath+fileName);
            // 如果文件名存在，则进行下载
            if (file.exists()) {
                // 配置
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                try {
                    response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // 文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    //io流输入
                    fis = new FileInputStream(file);
                    //保证文件的准确性
                    bis = new BufferedInputStream(fis);
                    //io流输出
                    ServletOutputStream os = response.getOutputStream();
                    int i = 0;
                    while ((i = bis.read(buffer)) != -1) {
                        os.write(buffer, 0, i);
                        os.flush();
                    }
                    //刷新，否则写入的时候写不全
                    System.out.println("下载成功");
                }
                catch (Exception e) {
                    System.out.println("下载失败");
                }
                //关流
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
         return files;
    }
}