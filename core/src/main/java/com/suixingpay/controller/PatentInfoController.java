package com.suixingpay.controller;

import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.User;
import com.suixingpay.service.PatentInfoService;
import com.suixingpay.service.UserDescriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 詹文良
 * @program: patent-pool-3th
 * @description: 专利接口 Controller
 * <p>
 * Created by Jalr4ever on 2019/11/21.
 */

@RestController
@Slf4j

public class PatentInfoController {

    @Autowired
    private PatentInfoService patentInfoService;

    @Autowired
    private UserDescriptionService userDescriptionService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/addPatent")
    public String addNewPatent(@RequestBody PatentInfo patentInfo) {

        // TODO: 2019/11/26 测试通过，可作为生产代码
        String addPatentResult = patentInfoService.createNewPatent(patentInfo);
        return addPatentResult;

    }

    @RequestMapping("/searchPatent")
    public String searchPatentFuzzy(@RequestBody PatentInfo patentInfo) {

        // TODO: 2019/11/26 前端测试通过，跟测试对接口
        User user = userDescriptionService.userDescription(httpServletRequest.getSession());
        String searchPatentResult = patentInfoService.searchPatentByUserType(patentInfo, user.getId());
        return searchPatentResult;

    }

    @RequestMapping("/searchPatentPool")
    public String searchPatentPool() {

        // TODO: 2019/11/26 前端测试通过，跟测试对接口
        User user = userDescriptionService.userDescription(httpServletRequest.getSession());
        String searchPatentPoolResult = patentInfoService.searchNavigationInfo(user.getId());
        return searchPatentPoolResult;

    }

    @RequestMapping("/receivePatent")
    public String receiveOnePatent(@RequestBody PatentInfo patentInfo) {

        // TODO: 2019/11/26 前端测试通过，跟测试对接口
        User user = userDescriptionService.userDescription(httpServletRequest.getSession());
        patentInfo.setOwnerUserId(user.getId());
        String receivePatentResult = patentInfoService.receivePatent(patentInfo);
        return receivePatentResult;

    }

    @RequestMapping("/editPatent")
    public String editPatentById(@RequestBody PatentInfo patentInfo) {

        // TODO: 2019/11/26 前端测试通过，跟测试对接口
        User user = userDescriptionService.userDescription(httpServletRequest.getSession());
        String editPatentResult = patentInfoService.editPatent(patentInfo, user.getId());
        return editPatentResult;

    }

    @RequestMapping("/getPatentDetail")
    public String showPatentDetails(PatentInfo patentInfo) {

        // TODO: 2019/11/26 前端测试通过，跟测试对接口
        //由于只传入专利的 id 即可，则会返回只有一个专利的 List
        String showPatentDetailsResult = patentInfoService.searchPatentAnyCondition(patentInfo);
        return showPatentDetailsResult;

    }

}
