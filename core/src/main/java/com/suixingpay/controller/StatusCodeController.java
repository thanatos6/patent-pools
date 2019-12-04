package com.suixingpay.controller;

import com.suixingpay.aspect.Action;
import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.RejectContent;
import com.suixingpay.service.StatusCodeService;
import com.suixingpay.service.UserDescriptionService;
import com.suixingpay.util.ZhuanliUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public String wait(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = userDescriptionService.userDescription(session).getId();
        Integer roleId = userDescriptionService.userDescription(session).getStatus();
        if (userId == null || roleId == null) {
            return ZhuanliUtil.getJSONString("传参为空");

        } else {
            String list1 = statusCodeService.selectCodeByRole(roleId, userId);
            return list1;
        }

    }


    //管理员点击同意按钮，改变专利当前状态，前端测试通过
    @Action(name="agree")
    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    @ResponseBody
    public String agree(@RequestBody PatentInfo patentInfo) {
        Integer pid = patentInfo.getId();
        if (patentInfo == null || pid == null) {
            return ZhuanliUtil.getJSONString("传参为空");
        } else {
            return statusCodeService.updateStatusPass(pid);
        }

    }


    //管理员点击驳回按钮，改变专利当前状态，并且插入驳回列表一条信息
    @Action(name="reject")
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    @ResponseBody
    public String reject(@RequestBody RejectContent rejectContent) {
        if (rejectContent == null) {
            return ZhuanliUtil.getJSONString("传参为空");
        } else {
            return statusCodeService.createNewReject(rejectContent);
        }
    }


    //撰写人点击认领，将状态改为编写中，前端测试通过
    @RequestMapping(value = "/claim", method = RequestMethod.POST)
    public String claim(@RequestBody PatentInfo patentInfo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = userDescriptionService.userDescription(session).getId();
        if (patentInfo == null || userId == null) {
            return ZhuanliUtil.getJSONString("传参为空");
        } else {
            patentInfo.setOwnerUserId(userId);
            return statusCodeService.updateStatusClaim(patentInfo);
        }

    }


    //根据认领人ID查看已认领未撰写的专利 ，状态码为4
    @RequestMapping(value = "/claimed", method = RequestMethod.POST)
    public String claimd(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = userDescriptionService.userDescription(session).getId();
        if (userId == null) {
            return ZhuanliUtil.getJSONString("传参为空");
        } else {
            PatentInfo patentInfo = new PatentInfo();
            patentInfo.setOwnerUserId(userId);
            return statusCodeService.selectPatentByclaimed(patentInfo);
        }

    }


    //根据认领人ID查看自己认领的待审批的专利 ，状态码为1,5,6
    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public String review(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PatentInfo patentInfo = new PatentInfo();
        Integer userId = userDescriptionService.userDescription(session).getId();
        if (userId == null) {
            return ZhuanliUtil.getJSONString("传参为空");
        } else {
            patentInfo.setOwnerUserId(userId);
            return statusCodeService.selectPatentByWaitMyself(patentInfo);
        }

    }


    //根据当前用户ID查看自己认领的待维护的专利列表 ，状态码为7提交成功
    @RequestMapping(value = "/success", method = RequestMethod.POST)
    public String success(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PatentInfo patentInfo = new PatentInfo();
        Integer userId = userDescriptionService.userDescription(session).getId();
        if (userId == null) {
            return ZhuanliUtil.getJSONString("传参为空");
        } else {
            patentInfo.setOwnerUserId(userId);
            return statusCodeService.selectPatentBySuccess(patentInfo);
        }

    }


    //根据当前用户点击一审被驳回的专利列表，获取专利列表Id,改状态11变0
    @RequestMapping(value = "/know", method = RequestMethod.POST)
    public boolean know(@RequestBody PatentInfo patentInfo) {
        if (patentInfo == null) {
            return false;
        } else {
            return statusCodeService.updateStatusFinish(patentInfo);
        }

    }


    //撰写人点击查看驳回原因，显示专利被驳回理由
    @RequestMapping(value = "/viewreason", method = RequestMethod.POST)
    public String know(@RequestBody RejectContent rejectContent) {
        if (rejectContent == null) {
            return ZhuanliUtil.getJSONString("传参为空");
        } else {
            return statusCodeService.selectPatentViewReason(rejectContent.getPatentId());
        }

    }
}
