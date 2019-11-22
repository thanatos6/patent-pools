package com.suixingpay.mapper;

import com.suixingpay.bean.Files;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface FileMapper {

    int insert(Files data);
    void update(int fileId);
    List<Files> selectById(int filePatentId);
    Files selectPathByFileId(int fileId);
}
