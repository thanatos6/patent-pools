package com.suixingpay.controller;


import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.service.PatentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 詹文良
 * @program: patent-pool-3th
 * @description: 本地测试用的 Controller
 * <p>
 * Created by Jalr4ever on 2019/11/21.
 */
@RestController
@Slf4j
public class PatentInfoController {

    @Autowired
    private PatentInfoService patentInfoService;



    @RequestMapping("/addPatent")
    public String addNewPatent(@RequestBody PatentInfo patentInfo) {
        log.warn(patentInfo.getApplyTechLinkman());
        String addPatentResult = patentInfoService.createNewPatent(patentInfo);
        return addPatentResult;
    }

    @RequestMapping("/searchPatent")
    public String searchPatentFuzzy(@RequestBody PatentInfo patentInfo) {

        String searchPatentResult = patentInfoService.searchPatentAnyCondition(patentInfo);
        return searchPatentResult;
    }


}
