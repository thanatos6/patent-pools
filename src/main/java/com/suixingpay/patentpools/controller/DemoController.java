package com.suixingpay.patentpools.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/")
    public String index() {
        return "kongjian";
    }

    @GetMapping("/test")
    public String test(@RequestParam("userName") String userName) {
        return "hello world";
    }
}
