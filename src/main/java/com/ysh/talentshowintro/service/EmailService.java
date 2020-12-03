package com.ysh.talentshowintro.service;

import com.ysh.talentshowintro.mapper.EmailMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    final
    EmailMapper emailMapper;

    public EmailService(EmailMapper emailMapper) {
        this.emailMapper = emailMapper;
    }

    public List<String> getAllEmails() {
        return emailMapper.getAllEmails();
    }

    public boolean isVoted(String email) {
        return emailMapper.isVoted(email);
    }

    public void vote(String email) {
        emailMapper.vote(email);
    }
}

