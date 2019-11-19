package com.suixingpay.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {
    @RequestMapping("/hi")
    public String hello() {
        return "hello world";
    }
}
