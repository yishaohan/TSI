package com.ysh.talentshowintro.service;

import com.ysh.talentshowintro.mapper.MessageMapper;
import com.ysh.talentshowintro.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageMapper messageMapper;

    public void save(Message message) {
        messageMapper.save(message);
    }

}
