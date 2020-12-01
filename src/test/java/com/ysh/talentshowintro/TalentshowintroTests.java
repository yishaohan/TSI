package com.ysh.talentshowintro;

import com.ysh.talentshowintro.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
class TalentshowintroTests {

    @Autowired
    MailService mailService;

    @Autowired
    TemplateEngine templateEngine;

    void contextLoads() {
    }

    //    @Test
    void mailSend() {
//        mailService.sendSimpleMail("yicunzhi@gmail.com", "yicunzhi@icloud.com", "yicunzhi@msn.com", "测试邮件主题", "测试邮件内容");
        Context ctx = new Context();
        ctx.setVariable("username", "小胖胖");
        String mail = templateEngine.process("mail.html", ctx);
        mailService.sendSimpleMail("yicunzhi@gmail.com", "yicunzhi@icloud.com", "yicunzhi@msn.com", "测试邮件主题", mail);
    }
}
