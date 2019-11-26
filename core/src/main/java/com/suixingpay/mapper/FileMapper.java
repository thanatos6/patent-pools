package com.suixingpay.mapper;

import com.suixingpay.pojo.Files;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author duansiyu
 */
@Mapper
@Component
public interface FileMapper {
    /**
     * 将上传文件的信息保存到数据库中
     *
     * @param data
     * @return
     */
    int insert(Files data);

    /**
     * 根据文件的Id进行逻辑删除。
     * @param fileId
     * @return
     */
    int update(int fileId);

    /**
     * 根据专利Id查询该Id下的所有文件
     * @param filePatentId
     * @return
     */
    List<Files> selectById(int filePatentId);

    /**
     * 为了删除文件的查询功能
     * @param fileId
     * @return
     */
    Files selectPathByFileId(int fileId);
}
