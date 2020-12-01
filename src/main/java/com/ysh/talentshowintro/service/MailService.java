package com.ysh.talentshowintro.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailService {

    final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleMail(String from, String to, String cc, String subject, String content) {
//        SimpleMailMessage simpMsg = new SimpleMailMessage();
//        simpMsg.setFrom(from);
//        simpMsg.setTo(to);
//        simpMsg.setCc(cc);
//        simpMsg.setSubject(subject);
//        simpMsg.setText(content);
//        javaMailSender.send(simpMsg);
//        File file = new File("d:/nohup.out");
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
//            helper.addAttachment(file.getName(), file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
