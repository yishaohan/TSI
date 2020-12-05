package com.ysh.talentshowintro.service;

import com.ysh.talentshowintro.mapper.AppParamMapper;
import com.ysh.talentshowintro.model.AppParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppParamService {
    final AppParamMapper appParamMapper;

    public AppParamService(AppParamMapper appParamMapper) {
        this.appParamMapper = appParamMapper;
    }

    public String getAppParamByKey(String key) {
        return appParamMapper.getAppParamByKey(key);
    }

    public void setAppParamByKey(String key, String value) {
        appParamMapper.setAppParamByKey(key, value);
    }
}
