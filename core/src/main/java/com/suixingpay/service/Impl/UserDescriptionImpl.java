package com.suixingpay.service.Impl;


import com.suixingpay.pojo.User;
import com.suixingpay.service.UserDescriptionService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserDescriptionImpl implements UserDescriptionService {
    @Override
    public User userDescription(HttpSession session) {
        return  (User)session.getAttribute("user");
    }
}
