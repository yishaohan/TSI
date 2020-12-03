package com.ysh.talentshowintro.service;

import com.ysh.talentshowintro.mapper.CustomOrderMapper;
import com.ysh.talentshowintro.model.CustomOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    CustomOrderMapper customOrderMapper;

    public void save(CustomOrder customOrder) {
        customOrderMapper.save(customOrder);
    }

    public List<CustomOrder> getAllOrder() {
        return customOrderMapper.getAllOrder();
    }
}
