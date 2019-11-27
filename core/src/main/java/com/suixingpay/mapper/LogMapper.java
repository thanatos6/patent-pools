package com.suixingpay.mapper;

import com.suixingpay.pojo.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface LogMapper {


    int insert(Log record);


    List<Log> selectLogById(Integer id);


    List<Log> selectAllLog();
}