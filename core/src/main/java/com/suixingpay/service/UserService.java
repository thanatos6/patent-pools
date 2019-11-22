package com.suixingpay.service;

import com.suixingpay.pojo.User;

public interface UserService {
    User login(String account,String password);
    User userByName(String name);
}

