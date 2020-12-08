package com.ysh.talentshowintro;

import com.paypal.http.HttpResponse;
import com.paypal.http.serializer.Json;
import com.paypal.orders.Order;
import com.paypal.orders.OrdersGetRequest;
import com.ysh.talentshowintro.paypal.Credentials;
import com.ysh.talentshowintro.Tasks.SendMailTask;
import com.ysh.talentshowintro.service.TicketService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
class TalentshowintroTests {

    @Autowired
    SendMailTask sendMailTask;

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    TicketService ticketService;

    void contextLoads() {
    }

    //    @Test
    void mailSend() {
//        sendMailTask.sendMail();
    }

    //    @Test
    void paypal() throws Exception {
//        OrdersGetRequest request = new OrdersGetRequest("59L40591CR683515R");
//        HttpResponse<Order> response = Credentials.paypalClient.execute(request);
//        Order order = ((Order) response.result());
//        System.out.println(new JSONObject(new Json().serialize(response.result())).toString(4));
    }

    //    @Test
    public void test() {
//        System.out.println(ticketService.getTicketByID(70));
    }
}
