package com.suixingpay.service.Impl;

import com.suixingpay.mapper.UserMapper;

import com.suixingpay.pojo.User;

import com.suixingpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Date;


@Service
public class UserImpl implements UserService {

    @Autowired
    UserMapper userDao;

    public User selectByname(String name){
        return userDao.userByName(name);
    }


    public User login(String account,String password){
       User user = userDao.userByAccountAndPassword(account,password);
        System.out.println(user);
        return  user;
    }


    @Override
    public User userByName(String name) {
        return new User();
    }
}
