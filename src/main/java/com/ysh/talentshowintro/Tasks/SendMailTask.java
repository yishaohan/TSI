package com.ysh.talentshowintro.Tasks;

import com.ysh.talentshowintro.model.MailQueue;
import com.ysh.talentshowintro.model.Message;
import com.ysh.talentshowintro.service.MailQueueService;
import com.ysh.talentshowintro.service.MessageService;
import com.ysh.talentshowintro.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

@Component
public class SendMailTask {

    final JavaMailSender javaMailSender;
    final MailQueueService mailQueueService;
    final MessageService messageService;

    public SendMailTask(JavaMailSender javaMailSender, MailQueueService mailQueueService, MessageService messageService) {
        this.javaMailSender = javaMailSender;
        this.mailQueueService = mailQueueService;
        this.messageService = messageService;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendMail() {
        MailQueue mail = mailQueueService.getOldestMailsMail();
        if (mail == null) {
            return;
        }
        Message message = new Message();
        message.setCreateDateTime(new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime()));
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(StringUtils.isNull(mail.getFrom()));
            helper.setTo(StringUtils.isNull(mail.getTo()));
            helper.setSubject(StringUtils.isNull(mail.getSubject()));
            helper.setBcc("henryyi2005@gmail.com");
            helper.setText(StringUtils.isNull(mail.getContext()), true);
            javaMailSender.send(mimeMessage);
            mailQueueService.setMailQueueStatus(mail.getId(), "Completed");
            message.setSubject("Mail sent successfully !");
        } catch (MessagingException e) {
            mailQueueService.setMailQueueStatus(mail.getId(), "Failed");
            message.setSubject("Failed to send mail !");
        } catch (Exception e) {
            mailQueueService.setMailQueueStatus(mail.getId(), "Failed");
            message.setSubject("Failed to send mail !");
        } finally {
            message.setMessage("mailID: " + mail.getId() + "  Date: " + mail.getCreateDateTime() + "  Status: " + mail.getStatus() + "  Form: " + mail.getFrom() + "  To: " + mail.getTo() + "  cc: " + StringUtils.isNull(mail.getCc()) + "  bcc: " + StringUtils.isNull(mail.getBcc()) + "  Subject: " + StringUtils.isNull(mail.getSubject()));
            messageService.save(message);
        }
    }
}
