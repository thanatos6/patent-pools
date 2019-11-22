package com.suixingpay.service;

import com.suixingpay.pojo.User;

import javax.servlet.http.HttpSession;

public interface UserDescriptionService {
    User userDescription(HttpSession session);
}
