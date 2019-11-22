package com.suixingpay.service;

import com.suixingpay.pojo.PatentProperties;

import java.util.List;

public interface PatentPropertiesService {
    PatentProperties searchPatentProperties(int id);

    List<PatentProperties> searchPatentPropertiesByName(String name);

    void savePatentProperties(PatentProperties patentProperties);

    void removePatentProperties(PatentProperties patentProperties);
}
