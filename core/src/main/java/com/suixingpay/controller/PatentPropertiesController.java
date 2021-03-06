package com.suixingpay.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suixingpay.pojo.PatentProperties;
import com.suixingpay.pojo.PatentPropertiesList;
import com.suixingpay.service.PatentPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @ResponseBody
    public String getProperties() {
        return patentPropertiesService.searchPatentProperties(1).getIndicatorName();
    }

    @RequestMapping("/get-by-patent-id")
    @ResponseBody
    public String propertiesByPatentId(@RequestParam("patentId") Integer patentId) {
        List<PatentProperties> patentPropertiesByPatentId = patentPropertiesService.searchPatentPropertiesByPatentId(patentId);
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("code", 0);
        mapResult.put("result", patentPropertiesByPatentId);
        String text = JSON.toJSONString(mapResult);
        return text;
    }

    @RequestMapping("/patent-by-properties-name")
    @ResponseBody
    public List<PatentProperties> propertiesByName (@RequestParam("name") String name) {
        List<PatentProperties> patentListByPropertiesName = patentPropertiesService.searchPatentPropertiesByName(name);
        return patentListByPropertiesName;
    }

    @RequestMapping("/save-properties")
    @ResponseBody
    public String saveProperties(@RequestParam("name") String name, @RequestParam("patentId") Integer patentId) {
        PatentProperties patentProperties = new PatentProperties();
        patentProperties.setPatentId(patentId);
        patentProperties.setIndicatorName(name);
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        patentProperties.setCreateDate(now);
        patentProperties.setModifyDate(now);
        patentPropertiesService.savePatentProperties(patentProperties);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "添加成功");
        String text = JSON.toJSONString(result);
        return text;
    }

    @RequestMapping("/remove-properties")
    @ResponseBody
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

    @RequestMapping("/join-patent")
    @ResponseBody
    public String getPropertiesJoinPatent(@RequestParam("name") String name,
                                            @RequestParam("pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 20);
        List<PatentPropertiesList> result = patentPropertiesService.searchPropertiesJoinPatent(name);
        PageInfo page = new PageInfo(result);

        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("code", 0);
        mapResult.put("result", page);
        String text = JSON.toJSONString(mapResult);
        return text;
    }
}
