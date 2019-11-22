package com.suixingpay.service;

import com.alibaba.fastjson.JSON;
import com.suixingpay.mapper.PatentInfoMapper;
import com.suixingpay.pojo.PatentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 詹文良
 * @program: patent-pool-3th
 * @description: 专利信息的业务逻辑服务具体实现
 * <p>
 * Created by Jalr4ever on 2019/11/21.
 */

@Service
@Slf4j
public class PatentInfoServiceImpl implements PatentInfoService {

    @Resource
    private PatentInfoMapper patentInfoMapper;

    @Override
    public String createNewPatent(PatentInfo patentInfo) {

        // 定义要返回的 JSON 结果
        String returnJsonStr = "";

        // 在新建专利的服务逻辑层初始化其申请日期、创建日期、修改日期
        Date date = new Date();
        patentInfo.setApplyDate(date);
        patentInfo.setCreateDate(date);
        patentInfo.setModifyDate(date);


        // 通过 mapper 的执行来决定是否成功，插入失败，则返回结果为 0；插入成功，则 mapper 执行结果为 1
        int insertResult = 0;
        try {
            insertResult = patentInfoMapper.insertPatentInfo(patentInfo);
        } catch (Exception e) {
            log.error("数据库插入专利错误！");
            log.error(e.getMessage());
            e.printStackTrace();
        }

        // 决定返回的 status 返回结果，如果为 1 则表示成功，
        String insertStatusValue = insertResult == 1 ? "success" : "failed";
        Map<String, String> insertStatus = new HashMap<>(1);
        insertStatus.put("patentStatus", insertStatusValue);

        // 解析 JSON 返回结果
        returnJsonStr = JSON.toJSONString(insertStatus);

        return returnJsonStr;
    }

    @Override
    public String searchPatentAnyCondition(PatentInfo patentInfo) {

        // 定义要返回的 JSON 结果
        String returnJsonStr = "";

        List<PatentInfo> searchPatentList = patentInfoMapper.selectPatentFuzzy(patentInfo);

        // 根据 mapper 的执行来判断，如果为空，则返回失败状态 JSON 串，如果不为空，返回 LIST JSON 串
        if (searchPatentList.isEmpty()) {

            // 写入错误的 status 信息
            Map<String, String> resutlStatusInfo = new HashMap<>(1);
            resutlStatusInfo.put("patentStatus", "failed");
            returnJsonStr = JSON.toJSONString(resutlStatusInfo);

        } else {
            returnJsonStr = JSON.toJSONString(searchPatentList);
        }
        return returnJsonStr;
    }
}
