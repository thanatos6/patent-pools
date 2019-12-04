package com.suixingpay.controller;

import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.service.StatusCodeService;
import com.suixingpay.service.UserDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/11/20 23:32
 * 待办事项页面功能
 */
@RestController
@RequestMapping("/approval")
public class StatusCodeController {
    @Autowired
    private StatusCodeService statusCodeService;
    @Autowired
    private UserDescriptionService userDescriptionService;


    //根据登陆者角色码查找相应的待办，分为管理员1、撰写人0
    @RequestMapping(value = "/wait", method = RequestMethod.POST)
    public String wait(HttpSession session) {

        int userId = userDescriptionService.userDescription(session).getId();
        int roleId = userDescriptionService.userDescription(session).getStatus();
        String list1 = statusCodeService.selectCodeByRole(roleId, userId);
        return list1;
    }


    //管理员点击同意按钮，改变专利当前状态，前端测试通过
    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    @ResponseBody
    public String agree(@RequestBody PatentInfo patentInfo) {
        int pid = patentInfo.getId();
        return statusCodeService.updateStatusPass(pid);
    }

    //管理员点击驳回按钮，改变专利当前状态，，前端测试通过
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    @ResponseBody
    public String reject(@RequestBody PatentInfo patentInfo) {
        int pid = patentInfo.getId();
        return statusCodeService.updateStatusReject(pid);

    }


    //撰写人点击认领，将状态改为编写中，前端测试通过
    @RequestMapping(value = "/claim", method = RequestMethod.POST)
    public String claim(@RequestBody PatentInfo patentInfo, HttpSession session) {

        int userId = userDescriptionService.userDescription(session).getId();
        patentInfo.setOwnerUserId(userId);
        return statusCodeService.updateStatusClaim(patentInfo);
    }


}
