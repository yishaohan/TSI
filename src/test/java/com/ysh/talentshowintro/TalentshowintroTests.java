package com.ysh.talentshowintro;

import com.paypal.http.HttpResponse;
import com.paypal.http.serializer.Json;
import com.paypal.orders.Order;
import com.paypal.orders.OrdersGetRequest;
import com.ysh.talentshowintro.mapper.CustomOrderMapper;
import com.ysh.talentshowintro.paypal.Credentials;
import com.ysh.talentshowintro.Tasks.SendMailTask;
import com.ysh.talentshowintro.service.OrderService;
import com.ysh.talentshowintro.service.TicketService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;

@SpringBootTest
class TalentshowintroTests {

    @Autowired
    SendMailTask sendMailTask;

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    TicketService ticketService;

    @Autowired
    OrderService orderService;

    void contextLoads() {

    }

}
