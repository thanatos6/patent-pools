package com.suixingpay.service.serviceimpl;

import com.suixingpay.mapper.StatusCodeMapper;
import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.StatusCode;
import com.suixingpay.service.StatusCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/11/20 22:15
 */
@Service
public class StatusCodeServiceImpl implements StatusCodeService {
    @Autowired
    private StatusCodeMapper statusCodeMapper;

    //审批通过
    @Override
    public String updateStatusPass(int patentID) {
        String url = "";
        try {
            statusCodeMapper.updateStatusPass(patentID);
            url = "200";
        } catch (Exception e) {
            url = "失败";
            e.printStackTrace();
        }
        return url;
    }

    //审批驳回
    @Override
    public String updateStatusReject(int patentID) {
        String url = "";
        try {
            statusCodeMapper.updateStatusReject(patentID);
            url = "200";

        } catch (Exception e) {
            url = "失败";
            e.printStackTrace();
        }

        return url;
    }

    //用于页面撰写人的待办事件的状态码转化
    // 不管是哪次驳回，都要把状态码改为编写中15变4，16变4，编写中
    @Override
    public boolean updateStatusWriter(int patentID) {
        boolean result = false;
        try {
            statusCodeMapper.updateStatusWriter(patentID);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //点击认领
    @Override
    public String updateStatusClaim(PatentInfo patentInfo) {
        String url = "";
        try {
            statusCodeMapper.updateStatusClaim(patentInfo);
            url = "200";
        } catch (Exception e) {
            url = "失败";
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public boolean updateStatusFinish(int patentId) {
        boolean result = false;
        try {
            statusCodeMapper.updateStatusFinish(patentId);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List selectCodeByRole(int role) {


        StringBuffer sb = new StringBuffer();
        List<StatusCode> statusCode = statusCodeMapper.selectCodeByRole(role);
        //List<Integer> List1 = new List<Integer>
        for (int i = 0; i < statusCode.size(); i++) {
            sb.append(statusCode.get(i).getProcessCode());
        }

        return statusCode;
    }

}
