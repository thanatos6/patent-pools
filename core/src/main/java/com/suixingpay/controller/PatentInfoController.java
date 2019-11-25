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

import javax.servlet.http.HttpSession;

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

    @RequestMapping("/addPatent")
    public String addNewPatent(@RequestBody PatentInfo patentInfo) {
        log.warn(patentInfo.getApplyTechLinkman());
        String addPatentResult = patentInfoService.createNewPatent(patentInfo);
        return addPatentResult;
    }

    @RequestMapping("/searchPatent")
    public String searchPatentFuzzy(@RequestBody PatentInfo patentInfo, HttpSession httpSession) {

        // 获取当前登录的用户 id
        User user = userDescriptionService.userDescription(httpSession);
        int testGetId = user.getId();
        patentInfo.setId(user.getId());

        // TODO: 2019/11/22 拿到用户 ID 之后做的事情 --> 在服务层调用 <-- 服务层的分类查询
        // TODO: 2019/11/24 错误。该 ID 不能传递进服务层，除非方法重构，讨论一下是否能在服务层获取当前登录用户？
        // TODO: 2019/11/25 尝试在controller层处

        String searchPatentResult = patentInfoService.searchPatentAnyCondition(patentInfo);
        return searchPatentResult;
    }

    @RequestMapping("/searchPatentPool")
    public String searchPatentPool(HttpSession httpSession) {
        // 获取当前登录的用户 id
        User user = userDescriptionService.userDescription(httpSession);
        int testGetId = user.getId();
        String searchPatentPoolResult = patentInfoService.searchNavigationInfo(user.getId());

        // 暂时传入管理员 id
        searchPatentPoolResult = patentInfoService.searchNavigationInfo(4);
        return searchPatentPoolResult;
    }


    @RequestMapping("/receivePatent")
    public String receiveOnePatent(PatentInfo patentInfo, HttpSession session) {
        // TODO: 2019/11/24 这里的 调用还没经过未登录情况的测试 - 未登录 去领取，应该是获取不到用户 ID ，应该直接报认领失败
        // TODO: 2019/11/24 错误。该 ID 不能传递进服务层，除非方法重构，讨论一下是否能在服务层获取当前登录用户？

//        User user = userDescriptionService.userDescription(httpSession);
//        patentInfo.setOwnerUserId(user.getId());

        // TODO: 2019/11/24 post man 测试通过，认领人暂时写死，为用户 id = 3 的用户
        patentInfo.setOwnerUserId(3);
        String receivePatentResult = patentInfoService.receivePatent(patentInfo);
        return receivePatentResult;
    }


    @RequestMapping("/editPatent")
    public String editPatentById(PatentInfo patentInfo) {
        // TODO: 2019/11/23 参数是自己调试的时候使用的, 因为 POST MAN 不支持 @RequestBody 对接前端, 要改回来
        String editPatentResult = patentInfoService.editPatent(patentInfo);
        return editPatentResult;
    }

    @RequestMapping("/getPatentDetail")
    public String showPatentDetails(PatentInfo patentInfo) {
        //由于只传入专利的 id 即可，则会返回只有一个专利的 List
        // TODO: 2019/11/24 post man 测试通过，这里是需要加注解与前端对接的状态
        String showPatentDetailsResult = patentInfoService.searchPatentAnyCondition(patentInfo);
        return showPatentDetailsResult;
    }


}
