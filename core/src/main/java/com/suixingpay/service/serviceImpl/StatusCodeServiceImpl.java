package com.suixingpay.service.serviceImpl;

import com.suixingpay.mapper.StatusCodeMapper;
import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.StatusCode;
import com.suixingpay.service.StatusCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/11/20 22:15
 */
@Service
public class StatusCodeServiceImpl implements StatusCodeService {
    @Autowired
    StatusCodeMapper statusCodeMapper;

    //审批通过
    @Override
    public boolean updateStatusPass(int patentID) {

        boolean result = false;
        try {
            statusCodeMapper.updateStatusPass(patentID);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //审批驳回
    @Override
    public boolean updateStatusReject(int patentID) {

        boolean result = false;
        try {
            statusCodeMapper.updateStatusReject(patentID);
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
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
    public boolean updateStatusClaim(PatentInfo patentInfo) {
        boolean result = false;
        try {
            statusCodeMapper.updateStatusClaim(patentInfo);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
    public List<StatusCode> selectCodeByRole(int role) {
        List<StatusCode> statusCode = statusCodeMapper.selectCodeByRole(role);
        return statusCode;
    }
}
