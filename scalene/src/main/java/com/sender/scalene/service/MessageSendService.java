package com.sender.scalene.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSendService {

    // JMS Template used to send data through to the MQ Server
    private JmsTemplate jmsTemplate;
    @Autowired
    public MessageSendService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    public void sendMessage(String channel, String message) {
        // Send message to channel
        jmsTemplate.convertAndSend(channel, message);
    }
}
