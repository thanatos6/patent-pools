package com.suixingpay.controller;

import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.service.StatusCodeService;
import com.suixingpay.service.UserDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/11/20 23:32
 */
@RestController
@RequestMapping("/approval")
public class StatusCodeController {
    @Autowired
    private StatusCodeService statusCodeService;
    @Autowired
    private UserDescriptionService userDescriptionService;

    //根据角色码查找有权限的待办
    @RequestMapping(value = "/wait", method = RequestMethod.POST)
    public List wait(HttpSession session) {

        int userId = userDescriptionService.userDescription(session).getId();
        int roleId = userDescriptionService.userDescription(session).getStatus();
        System.out.println(roleId);
        List list1 = statusCodeService.selectCodeByRole(roleId);
        Map<String, List<Integer>> commisionParam = new HashMap<>(2);
        List<Integer> userIdList = new ArrayList<>(1);
        if (roleId == 1) {
            // userId.add(1);
            //List<Integer> statusList;
            commisionParam.put("userId", null);
            commisionParam.put("statusList", list1);
            //管理员的待办事项
            return list1;
        }
        if (roleId == 0) {
            userIdList.add(userId);
            //List<Integer> statusList;
            commisionParam.put("userId", null);
            commisionParam.put("statusList", list1);
            //撰写人的待办事项
        }
        return list1;
    }


    //管理员点击同意按钮，改变专利当前状态ok
    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    @ResponseBody
    public String agree(@RequestBody PatentInfo patentInfo) {
        //System.out.println(patentInfo.getId());
        int pid = patentInfo.getId();
        String url = "";
        boolean result = statusCodeService.updateStatusPass(pid);
        if (result) {
            url = "200";
        } else {
            url = "失败";
        }
        return url;
    }

    //管理员点击驳回按钮，改变专利当前状态ok
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    @ResponseBody
    public String reject(@RequestBody PatentInfo patentInfo) {
        int pid = patentInfo.getId();
        String url = "";
        boolean result = statusCodeService.updateStatusReject(pid);
        if (result) {
            url = "200";
        } else {
            url = "失败";
        }
        return url;
    }

    //撰写人点击认领，将状态改为编写中ok
    @RequestMapping(value = "/claim", method = RequestMethod.POST)
    public String claim(@RequestBody PatentInfo patentInfo, HttpSession session) {
        //此处差一个session接userid
        int userId = userDescriptionService.userDescription(session).getId();
        //System.out.println("userId:"+userId);
        patentInfo.setOwnerUserId(userId);
        String url = "";
        boolean result = statusCodeService.updateStatusClaim(patentInfo);
        if (result) {
            url = "200";
        } else {
            url = "失败";
        }
        return url;
    }

    //1.用于页面撰写人的待办事件的状态码转化
    @RequestMapping(value = "/rewrite", method = RequestMethod.POST)
    public boolean rewrite(@RequestBody PatentInfo patentInfo) {
        int pid = patentInfo.getId();
        return statusCodeService.updateStatusWriter(pid);
    }
}
