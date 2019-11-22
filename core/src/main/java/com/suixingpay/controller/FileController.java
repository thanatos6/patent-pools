package com.suixingpay.controller;

import com.suixingpay.pojo.Files;
import com.suixingpay.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@Slf4j

public class FileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
        private FileService fileService;

    /**
     * 下载文件
     * @param FilePatentId
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("id") int FilePatentId ,@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "{\"result\": \"上传失败，请选择文件\"}";
        }
        //文件专利ID
        int  filePatentId=FilePatentId;
        //文件名
        String fileName = file.getOriginalFilename();
        //文件路径
        String filePath = "D://test/";
         UUID uuid=UUID.randomUUID();
        File dest = new File(filePath + uuid + fileName );
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            //上传到数据库中
            Files files = new Files();
            files.setFileCreateTime(new Date());
            files.setFileName(fileName);
            files.setFilePatentId(filePatentId);
            files.setFilePath(filePath + uuid);
            files.setFileStatus(1);

            fileService.insert(files);
          return "{\"result\": \"上传成功\"}";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "{\"result\": \"上传失败\"}";
    }

    /**
     * 用户查询操作，可以查专利下的所有状态为1的文件
     * @param PatentId
     * @return
     */
    @GetMapping("/select")
    public List<Files> selectById(@RequestParam("id") int PatentId){
        List<Files> files1 = fileService.selectById(PatentId);
        return files1;

    }

    /**
     *删除文件，逻辑删除，只更改状态，保留文件内容
     *1为可用，0为不可用。
     * @param fileID
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public String update(@RequestParam("fileId") int fileID){
        //文件ID
        int fileId =fileID;
        fileService.update(fileId);
        return "{\"result\": \"删除成功\"}";
    }

    @GetMapping("/download")
    public void selectPathByFileId(@Param("fileId") int fileId, HttpServletResponse response) throws UnsupportedEncodingException {
        //获取查询方法
        Files files = fileService.selectPathByFileId(fileId);
        //获取文件名
        String fileName =files.getFileName();
        /*获取文件地址
        地址格式为：
        正规路径加UUID
        例：D://test/419dfa74-f1a2-4694-87f8-c5616b8673c3
         */
        String filePath = files.getFilePath();
        System.out.println(filePath);
        if (fileName != null) {
            //设置文件路径
            File file = new File(filePath+fileName);
            // 如果文件名存在，则进行下载
            if (file.exists()) {
                // 配置
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
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
                        os.write(buffer, 0, buffer.length);
                    }
                    //刷新，否则写入的时候写不全
                    os.flush();


                    System.out.println("Download the file successfully!");
                }
                catch (Exception e) {
                    System.out.println("Download the file failed!");
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

    }
}
