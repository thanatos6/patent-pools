package com.suixingpay.mapper;

import com.suixingpay.pojo.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hyx
 */
@Mapper
@Component
public interface LogMapper {

    /**
     *
     * @param record
     * @return int
     * 进行日志插入功能
     */
    int insert(Log record);

    /**
     *根据ID查询日志
     *
     * @param id
     * @return List<Log>
     *
     */
    List<Log> selectLogById(Integer id);

    /**
     * 查询全部日志
     *
     * @return List<Log>
     */
    List<Log> selectAllLog();
}