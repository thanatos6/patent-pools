package com.suixingpay.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duansiyu
 */
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello spring";
    }

}
