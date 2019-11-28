package com.suixingpay.service.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.suixingpay.mapper.StatusCodeMapper;
import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.StatusCode;
import com.suixingpay.service.PatentInfoService;
import com.suixingpay.service.StatusCodeService;
import com.suixingpay.util.ZhuanliUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/11/20 22:15
 */
@Service
@Slf4j
public class StatusCodeServiceImpl implements StatusCodeService {
    @Autowired
    private StatusCodeMapper statusCodeMapper;
    @Autowired
    private PatentInfoService patentInfoService;
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusCodeServiceImpl.class);

    /**
     * 审批通过，点击同意，根据专利Id号，点击同意按钮，改变流程状态
     *
     * @param patentID 专利ID号
     * @return
     */
    @Override
    public String updateStatusPass(int patentID) {

        try {
            int statusCode = statusCodeMapper.selectCodeByPid(patentID);
            if (statusCode == 1 || statusCode == 5 || statusCode == 6) {
                statusCodeMapper.updateStatusPass(patentID);
                return ZhuanliUtil.getJSONString("200");
            } else {
                return ZhuanliUtil.getJSONString("失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ZhuanliUtil.getJSONString("失败");
        }

    }


    /**
     * 点击驳回，根据专利Id号，改变流程状态
     *
     * @param patentID 专利ID号
     * @return
     */
    @Override
    public String updateStatusReject(int patentID) {

        try {
            int statusCode = statusCodeMapper.selectCodeByPid(patentID);
            if (statusCode == 5 || statusCode == 6) {
                statusCodeMapper.updateStatusReject(patentID);
                return ZhuanliUtil.getJSONString("200");
            } else if (statusCode == 1) {
                statusCodeMapper.updateStatusTalk(patentID);
                return ZhuanliUtil.getJSONString("200");
            } else {
                return ZhuanliUtil.getJSONString("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ZhuanliUtil.getJSONString("失败");
        }

    }


    /**
     * 点击认领该状态，根据专利Id号，改变流程状态
     *
     * @param patentInfo 专利对象
     * @return
     */
    @Override
    public String updateStatusClaim(PatentInfo patentInfo) {

        try {
            int patentId = patentInfo.getId();
            int statusCode = statusCodeMapper.selectCodeByPid(patentId);
            if (statusCode == 4) {
                return ZhuanliUtil.getJSONString("失败");
            } else {
                statusCodeMapper.updateStatusClaim(patentInfo);
                return ZhuanliUtil.getJSONString("200");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ZhuanliUtil.getJSONString("失败");

        }

    }


    /**
     * 点击编写完成，只有当状态为4编写中,15二审不通过,16提交审批不通过时，才允许走到下一步5待二审，根据专利Id号，改变流程状态
     *
     * @param patentInfo 专利实体
     * @return
     */
    @Override
    public boolean updateStatusFinish(PatentInfo patentInfo) {
        int code = patentInfo.getCurrentStatus();
        int patentId = patentInfo.getId();
        LOGGER.info("[接受的参数为{}和{}]", code, patentId);
        boolean result = false;
        if (code == 15 || code == 16 || code == 4) {
            statusCodeMapper.updateStatusFinish(patentId);
            result = true;
        }
        return result;
    }


    /**
     * 根据角色码查找有权限的待办
     *
     * @param userId 当前登录用户的ID号
     * @param role   当前登录用户的权限号，管理员为1，撰写人为0
     * @return
     */
    @Override
    public String selectCodeByRole(int role, int userId) {
        List<Integer> list = new ArrayList<Integer>();
        List<StatusCode> statusCode = statusCodeMapper.selectCodeByRole(role);
        List<PatentInfo> listQcPlate = new ArrayList<PatentInfo>();
        String result = "登陆者无权限！";
        if (role == 1) {
            //管理员的待办事项
            for (int i = 0; i < statusCode.size(); i++) {
                String sCode = statusCode.get(i).getProcessCode();
                int nCode = Integer.parseInt(sCode);
                list.add(nCode);
            }
            result = patentInfoService.searchPatentByCurrentStatusList(list, null);

            listQcPlate = JSON.parseArray(result, PatentInfo.class);
            return ZhuanliUtil.getJSONString(200, listQcPlate);
        }

        if (role == 0) {
            //撰写人的待办事项
            for (int i = 0; i < statusCode.size(); i++) {
                String sCode = statusCode.get(i).getProcessCode();
                int nCode = Integer.parseInt(sCode);
                list.add(nCode);
            }
            result = patentInfoService.searchPatentByCurrentStatusList(list, userId);

            listQcPlate = JSON.parseArray(result, PatentInfo.class);
            return ZhuanliUtil.getJSONString(200, listQcPlate);
        }
        return ZhuanliUtil.getJSONString(500, "");
    }


}
