package com.suixingpay.service.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.suixingpay.mapper.StatusCodeMapper;
import com.suixingpay.pojo.PatentInfo;
import com.suixingpay.pojo.RejectContent;
import com.suixingpay.pojo.StatusCode;
import com.suixingpay.service.PatentInfoService;
import com.suixingpay.service.StatusCodeService;
import com.suixingpay.service.UserDescriptionService;
import com.suixingpay.util.ZhuanliUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/11/20 22:15
 */
@Service
@Slf4j
public class StatusCodeServiceImpl implements StatusCodeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusCodeServiceImpl.class);
    @Autowired
    private StatusCodeMapper statusCodeMapper;
    @Autowired
    private PatentInfoService patentInfoService;
    @Autowired
    private UserDescriptionService userDescriptionService;
    @Autowired
    private HttpServletRequest request;


    /**
     * 审批通过，点击同意，根据专利Id号，点击同意按钮，改变流程状态
     *
     * @param patentID 专利ID号
     * @return
     */
    @Override
    public String updateStatusPass(int patentID) {

        try {
            Integer statusCode = statusCodeMapper.selectCodeByPid(patentID);
            if (statusCode == null) {
                return ZhuanliUtil.getJSONString("失败");
            }
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
     * 点击驳回按钮，改变专利状态，添加驳回原因
     *
     * @param rejectContent 驳回原因实体
     * @return
     */
    @Override
    @Transactional
    public String createNewReject(RejectContent rejectContent) {
        Integer patentid = rejectContent.getPatentId();
        HttpSession session = request.getSession();
        int userId = userDescriptionService.userDescription(session).getId();
        rejectContent.setRejectUserId(userId);

        // 在新建驳回原因表层初始化其创建日期、修改日期
        Date date = new Date();
        rejectContent.setCreateDate(date);
        rejectContent.setModifyDate(date);
        String getRejectContent = rejectContent.getRejectContent();
        // 通过 mapper 的执行来决定是否成功，插入失败，则返回结果为 0；插入成功，则 mapper 执行结果为 1
        try {
            if (patentid == null || getRejectContent == null || getRejectContent.equals("")) {
                return ZhuanliUtil.getJSONString("传参为空");
            }
            Integer statusCode = statusCodeMapper.selectCodeByPid(patentid);
            if (statusCode == null) {
                return ZhuanliUtil.getJSONString("失败");
            }
            if (statusCode == 5 || statusCode == 6) {
                statusCodeMapper.updateStatusReject(patentid);
                statusCodeMapper.insertRejectContent(rejectContent);
                return ZhuanliUtil.getJSONString("1");
            } else if (statusCode == 1) {
                statusCodeMapper.updateStatusTalk(patentid);
                statusCodeMapper.insertRejectContent(rejectContent);
                return ZhuanliUtil.getJSONString("1");
            }

        } catch (Exception e) {
            log.error("驳回原因表插入一条专利错误！");
            log.error(e.getMessage());
        }
        return ZhuanliUtil.getJSONString("0");
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
            Integer statusCode = statusCodeMapper.selectCodeByPid(patentId);
            if (statusCode == 4 || statusCode == null) {
                return ZhuanliUtil.getJSONString("失败");
            }
            statusCodeMapper.updateStatusClaim(patentInfo);
            return ZhuanliUtil.getJSONString("200");

        } catch (Exception e) {
            e.printStackTrace();
            return ZhuanliUtil.getJSONString("失败");

        }

    }


    /**
     * 点击编写完成，只有当状态为4编写中,15二审不通过,16提交审批不通过时，才允许走到下一步5待二审，
     * 根据专利Id号，改变流程状态
     * 当当前状态为0讨论中时，编写完成状态变为1待一审
     *
     * @param patentInfo 专利实体
     * @return
     */
    @Override
    public boolean updateStatusFinish(PatentInfo patentInfo) {
        Byte code = patentInfo.getCurrentStatus();
        boolean result = false;
        if (code == null) {
            return result;
        } else if (code == 15 || code == 16 || code == 4) {
            patentInfo.setCurrentStatus((byte) 5);
            statusCodeMapper.updateStatusFinish(patentInfo);
            result = true;
        } else if (code == 0) {
            patentInfo.setCurrentStatus((byte) 1);
            statusCodeMapper.updateStatusFinish(patentInfo);
            result = true;
        } else if (code == 11) {
            patentInfo.setCurrentStatus((byte) 0);
            statusCodeMapper.updateStatusFinish(patentInfo);
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


    /**
     * 根据认领人ID查看已认领未撰写的专利 ，状态码为4
     *
     * @param patentInfo 当前登录用户的ID号
     * @return
     */
    @Override
    public String selectPatentByclaimed(PatentInfo patentInfo) {
        try {
            List<PatentInfo> patentInfoList = statusCodeMapper.selectPatentByclaimed(patentInfo);
            return ZhuanliUtil.getJSONString(1, patentInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            return ZhuanliUtil.getJSONString("0");

        }
    }


    /**
     * 根据认领人ID查看自己认领的待审批的专利 ，状态码为1,5,6
     *
     * @param patentInfo 专利表对象
     * @return
     */
    @Override
    public String selectPatentByWaitMyself(PatentInfo patentInfo) {
        try {
            List<PatentInfo> patentInfoList = statusCodeMapper.selectPatentByWaitMyself(patentInfo);
            return ZhuanliUtil.getJSONString(1, patentInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            return ZhuanliUtil.getJSONString("0");

        }
    }


    /**
     * 根据当前用户ID查看自己认领的待维护的专利列表 ，状态码为7提交成功
     *
     * @param patentInfo 专利表对象
     * @return
     */
    @Override
    public String selectPatentBySuccess(PatentInfo patentInfo) {
        try {
            List<PatentInfo> patentInfoList = statusCodeMapper.selectPatentBySuccess(patentInfo);
            return ZhuanliUtil.getJSONString(1, patentInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            return ZhuanliUtil.getJSONString("0");

        }
    }


    /**
     * 撰写人点击查看驳回原因，显示专利被驳回理由
     *
     * @param patentId 专利ID号
     * @return
     */
    @Override
    public String selectPatentViewReason(int patentId) {

        // 如果执行异常, 则返回错误状态，如果不传字段，则全返回
        List<RejectContent> searchRejectContentList = null;
        try {
            searchRejectContentList = statusCodeMapper.selectPatentViewReason(patentId);
            return ZhuanliUtil.getJSONString(1, searchRejectContentList);
        } catch (Exception e) {
            log.error("驳回原因搜索错误！");
            log.error(e.getMessage());
        }
        return ZhuanliUtil.getJSONString("0");


    }
}
