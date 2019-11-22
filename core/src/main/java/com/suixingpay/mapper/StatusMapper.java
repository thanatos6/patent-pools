package com.suixingpay.mapper;

import com.suixingpay.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatusMapper {


    int selectStatusById(int id);
}
