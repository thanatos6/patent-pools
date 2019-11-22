package com.suixingpay.mapper;


import com.suixingpay.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {
    User userByAccountAndPassword(@Param("account") String account,@Param("password") String password);
    User userByName(@Param("name") String name);
}
