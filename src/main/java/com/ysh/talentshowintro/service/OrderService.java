package com.ysh.talentshowintro.service;

import com.ysh.talentshowintro.mapper.OrderMapper;
import com.ysh.talentshowintro.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    public void save(Order order) {
        orderMapper.save(order);
    }
}
