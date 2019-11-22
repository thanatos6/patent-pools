package com.suixingpay.controller;

import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.StatusCode;
import com.suixingpay.service.StatusCodeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    //根据角色码查找有权限的待办
    @RequestMapping(value = "/wait", method = RequestMethod.POST)
    public List<StatusCode> wait(HttpServletRequest request, HttpSession session) {

        String patentID = request.getParameter("id");
        int pid = Integer.parseInt(patentID);

        return statusCodeService.selectCodeByRole(pid);
    }

    //管理员点击同意按钮，改变专利当前状态
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

    //管理员点击驳回按钮，改变专利当前状态
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

    //撰写人点击认领，将状态改为编写中
    @RequestMapping(value = "/claim", method = RequestMethod.POST)
    public String claim(@RequestBody PatentInfo patentInfo) {
        //此处差一个session接userid
        patentInfo.setOwnerUserId(1);
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
