package com.cons.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSendService {

    // JMS Template used to send data through to the MMq Server
    private JmsTemplate jmsTemplate;
    @Autowired
    public MessageSendService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    public void sendMessage(String message) {{
    }
        jmsTemplate.convertAndSend("channel1", message);
    }
}
