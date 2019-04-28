package com.shibo.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service("producer")
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    public void sendMsg(Destination destination, final String message){
        jmsTemplate.convertAndSend(destination, message);
    }
}
