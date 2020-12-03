package com.ysh.talentshowintro.service;

import com.ysh.talentshowintro.mapper.MailQueueMapper;
import com.ysh.talentshowintro.model.MailQueue;
import com.ysh.talentshowintro.model.Message;
import com.ysh.talentshowintro.model.Ticket;
import com.ysh.talentshowintro.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class MailQueueService {

    final TemplateEngine templateEngine;
    final MailQueueMapper mailQueueMapper;

    public MailQueueService(TemplateEngine templateEngine, MailQueueMapper mailQueueMapper) {
        this.templateEngine = templateEngine;
        this.mailQueueMapper = mailQueueMapper;
    }

    public boolean addMail(String mailTo, String mailCc, String mailBcc, String subject, String template, List<Ticket> tickets, Message messages) {

        try {
            MailQueue mail = new MailQueue();
            if (mailTo == null || "".equals(mailTo) || !mailTo.contains("@")) {
                return false;
            }
            mail.setTo(mailTo);
            if (mailCc != null && !("".equals(mailCc)) && mailCc.contains("@")) {
                mail.setCc(mailCc);
            }
            if (mailBcc != null && !("".equals(mailBcc)) && mailBcc.contains("@")) {
                mail.setBcc(mailBcc);
            }
            mail.setSubject(StringUtils.isNull(subject));
            String context = "";
            if ("sendVoteMail".equals(template)) {
                Context ctx = new Context();
                ctx.setVariable("tickets", tickets);
                context = templateEngine.process("sendVoteMail.html", ctx);

            } else if ("sendWarnMail".equals(template)) {
                Context ctx = new Context();
                ctx.setVariable("message", messages);
                context = templateEngine.process("sendWarnMail.html", ctx);
            }
            mail.setContext(context);
            Timestamp createDateTime = new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime());
            mail.setCreateDateTime(createDateTime);
            mailQueueMapper.addMail(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public MailQueue getOldestMailsMail() {
        return mailQueueMapper.getOldestMail();
    }

    public void setMailQueueStatus(int mailID, String status) {
        mailQueueMapper.setMailQueueStatus(mailID, status);
    }
}
