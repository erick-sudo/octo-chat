package com.sender.scalene.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumerService {
    @JmsListener(destination="scalene")
    public void listener(String message) {
        System.out.println("----------------------------\n"+message+"\n----------------------------");
    }
}
