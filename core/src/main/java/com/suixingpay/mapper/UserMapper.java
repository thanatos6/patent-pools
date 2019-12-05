package com.suixingpay.mapper;


import com.suixingpay.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {
    User userByAccountAndPassword(@Param("account") String account,@Param("password") String password);
    User userByName(@Param("name") String name);
    int userByNumAndId(@Param("number") int number,@Param("id") int id);
    int selectNumById(@Param("id") int id);

}
