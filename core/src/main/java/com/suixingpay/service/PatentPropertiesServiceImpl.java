package com.suixingpay.service;

import com.github.pagehelper.PageHelper;
import com.suixingpay.pojo.PatentPropertiesList;
import org.springframework.boot.builder.ParentContextCloserApplicationListener;
import org.springframework.stereotype.Service;
import com.suixingpay.mapper.PatentPropertiesMapper;
import com.suixingpay.pojo.PatentProperties;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kongjian
 */
@Service
public class PatentPropertiesServiceImpl implements PatentPropertiesService {

    @Resource
    private PatentPropertiesMapper patentPropertiesMapper;

    @Override
    public PatentProperties searchPatentProperties(int id) {
        return patentPropertiesMapper.selectPatentPropertiesById(id);
    }

    @Override
    public void savePatentProperties(PatentProperties patentProperties) {
        patentPropertiesMapper.insertPatentProperty(patentProperties);
    }

    @Override
    public void removePatentProperties(PatentProperties patentProperties) {
        patentPropertiesMapper.deletePatentProperty(patentProperties);
    }

    @Override
    public List<PatentProperties> searchPatentPropertiesByName(String name) {
        PageHelper.startPage(1, 1);
        List<PatentProperties> patentPropertiesLikeName = patentPropertiesMapper.selectPatentPropertiesByName(name);
        return patentPropertiesLikeName;

//        List<PatentPropertiesList> result = new ArrayList<PatentPropertiesList>();
//        for (PatentProperties pProperties : patentPropertiesLikeName) {
//            pProperties.getIndicatorName();
//            PatentPropertiesList list = new PatentPropertiesList(
//                    pProperties.getPatentId(),
//                    pProperties.getIndicatorName(),
//                    "JL0083",
//                    0
//            );
//            result.add(list);
//        }
//        return result;
    }

    @Override
    public List<PatentProperties> searchPatentPropertiesByPatentId(int patentId) {
        List<PatentProperties> patentPropertiesByPatentId = patentPropertiesMapper.selectPatentPropertiesByPatentId(patentId);
        return patentPropertiesByPatentId;
    }

    @Override
    public List<PatentPropertiesList> searchPropertiesJoinPatent(String name) {
        List<PatentPropertiesList> propertiesJoinPatent = patentPropertiesMapper.selectPropertiesJoinPatent(name);
        return propertiesJoinPatent;
    }

    @Override
    public List<PatentPropertiesList> searchPropertiesJoinPatentEntity(PatentPropertiesList patentPropertiesList) {
        List<PatentPropertiesList> propertiesJoinPatent = patentPropertiesMapper.selectPropertiesJoinPatentEntity(patentPropertiesList);
        return propertiesJoinPatent;
    }
}
