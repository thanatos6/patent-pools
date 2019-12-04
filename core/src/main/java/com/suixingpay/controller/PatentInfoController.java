package com.suixingpay.controller;

import com.suixingpay.aspect.Action;
import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.User;
import com.suixingpay.service.PatentInfoService;
import com.suixingpay.service.UserDescriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    /**
     * 专利信息服务
     */
    @Autowired
    private PatentInfoService patentInfoService;

    /**
     * 用户信息服务
     */
    @Autowired
    private UserDescriptionService userDescriptionService;

    /**
     * HttpServletRequest 服务
     */
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/addPatent", method = RequestMethod.POST)
    public String addNewPatent(@RequestBody PatentInfo patentInfo) {

        // TODO: 2019/11/26 测试通过，可作为生产代码 - 正向通过，下一步反向测试
        String addPatentResult = patentInfoService.createNewPatent(patentInfo);
        return addPatentResult;

    }

    @RequestMapping(value = "/searchPatent", method = RequestMethod.POST)
    public String searchPatentFuzzy(@RequestBody PatentInfo patentInfo) {

        // TODO: 2019/11/26 前端测试通过，跟测试对接口 - 正向通过，下一步反向测试
        User user = userDescriptionService.userDescription(httpServletRequest.getSession());

        // 判断用户是否登录，若没登录，就赋值 id 为 0 表示没有登录
        int userId = user == null ? 0 : user.getId();
        String searchPatentResult = patentInfoService.searchPatentByUserType(patentInfo, userId);
        return searchPatentResult;

    }

    @RequestMapping(value = "/searchPatentPool", method = RequestMethod.GET)
    public String searchPatentPool() {

        // TODO: 2019/11/26 前端测试通过，跟测试对接口，正向通过，下一步反向测试
        User user = userDescriptionService.userDescription(httpServletRequest.getSession());
        String searchPatentPoolResult = patentInfoService.searchNavigationInfo(user.getId());
        return searchPatentPoolResult;

    }

    @RequestMapping(value = "/receivePatent", method = RequestMethod.POST)
    public String receiveOnePatent(@RequestBody PatentInfo patentInfo) {

        // TODO: 2019/11/26 前端测试通过，跟测试对接口，正向通过，下一步反向测试
        User user = userDescriptionService.userDescription(httpServletRequest.getSession());
        patentInfo.setOwnerUserId(user.getId());
        String receivePatentResult = patentInfoService.receivePatent(patentInfo);
        return receivePatentResult;

    }

    @Action(name="editPatentById")
    @RequestMapping(value = "/editPatent", method = RequestMethod.POST)
    public String editPatentById(@RequestBody PatentInfo patentInfo) {

        // TODO: 2019/11/26 前端测试通过，跟测试对接口，正向通过，下一步反向测试
        User user = userDescriptionService.userDescription(httpServletRequest.getSession());
        String editPatentResult = patentInfoService.editPatent(patentInfo, user.getId());
        return editPatentResult;
    }

    @RequestMapping(value = "/getPatentDetail", method = RequestMethod.POST)
    public String showPatentDetails(@RequestBody PatentInfo patentInfo) {

        // TODO: 2019/11/26 前端测试通过，跟测试对接口，正向通过，下一步反向测试考虑（id 不为 0 已经测试）
        //由于只传入专利的 id 即可，则会返回只有一个专利的 List
        String showPatentDetailsResult = patentInfoService.searchPatentAnyCondition(patentInfo);
        return showPatentDetailsResult;

    }

}
