package com.suixingpay.service;

import com.suixingpay.bean.Files;
import com.suixingpay.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileMapper fileMapper;
    @Override
    public int insert(Files data){ return fileMapper.insert(data); }

    @Override
    public void update(int fileId) {
        fileMapper.update(fileId);
    }

    @Override
    public List<Files> selectById(int filePatentId) {
        return fileMapper.selectById(filePatentId);
    }

    @Override
    public Files selectPathByFileId(int fileId) { return fileMapper.selectPathByFileId(fileId); }

}