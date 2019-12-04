package com.suixingpay.service;

import com.suixingpay.pojo.User;

public interface UserService {
    User login(String account,String password);
    User userByName(String name);
    //查询标记
    int userByNumAndId(int number,int id);
    //根据id查询num
    int selectNumById(int id);
}

