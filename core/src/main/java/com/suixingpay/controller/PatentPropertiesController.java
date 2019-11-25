package com.suixingpay.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mysql.fabric.xmlrpc.base.Array;
import com.suixingpay.pojo.PatentProperties;
import com.suixingpay.pojo.PatentPropertiesList;
import com.suixingpay.service.PatentPropertiesService;
import com.suixingpay.service.UserLoginDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author kongjian
 */
@RestController
@RequestMapping("/properties")
public class PatentPropertiesController {
    @Autowired
    private PatentPropertiesService patentPropertiesService;

    @RequestMapping("/search-patent")
    public String getProperties() {
        return patentPropertiesService.searchPatentProperties(1).getIndicatorName();
    }

    @RequestMapping("/get-by-patent-id")
    public List<PatentProperties> propertiesByPatentId(@RequestParam("patentId") Integer patentId) {
        List<PatentProperties> patentPropertiesByPatentId = patentPropertiesService.searchPatentPropertiesByPatentId(patentId);
        return patentPropertiesByPatentId;
    }

    @RequestMapping("/patent-by-properties-name")
    public List<PatentProperties> propertiesByName (@RequestParam("name") String name) {
        List<PatentProperties> patentListByPropertiesName = patentPropertiesService.searchPatentPropertiesByName(name);
        return patentListByPropertiesName;
    }

    @RequestMapping("/save-properties")
    public String saveProperties(@RequestParam("name") String name, @RequestParam("patentId") Integer patentId) {
        // Map<String, Object> userInfo = UserLoginDemoService.getUserInfo();
        PatentProperties patentProperties = new PatentProperties();
        patentProperties.setPatentId(patentId);
        patentProperties.setIndicatorName(name);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        patentProperties.setCreateDate(now);
        patentProperties.setModifyDate(now);
        patentPropertiesService.savePatentProperties(patentProperties);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "添加成功");
//        Map<String,Object> map = new HashMap<>();
//        map.put("code",0);
//        map.put("msg","访问成功");
//        map.put("result",users);
        String text = JSON.toJSONString(result);
        return text;
    }

    @RequestMapping("/remove-properties")
    public String removeProperties(@RequestParam("id") Integer id) {
        PatentProperties patentProperties = new PatentProperties();
        patentProperties.setId(id);
        patentPropertiesService.removePatentProperties(patentProperties);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "删除成功");
        String text = JSON.toJSONString(result);
        return text;
    }

    @RequestMapping("/patent-by-properties-name-test")
    public List<PatentPropertiesList> test1() {
        List<PatentPropertiesList> result = new ArrayList<PatentPropertiesList>();
        for (int i = 0; i < 3; i++) {
            PatentPropertiesList list = new PatentPropertiesList();
            list.setPatentId(1);
            list.setPropertiesTitle("make Laugh");
            list.setCode("hahahahha");
            list.setStatus(2);
            list.setApplyDate("2019-11-21 11:11:11");
            list.setCodingPerson("王伟");
            list.setStatusName("待审核");
            list.setPerson("李梅");
            result.add(list);
        }
        return result;
    }
}
