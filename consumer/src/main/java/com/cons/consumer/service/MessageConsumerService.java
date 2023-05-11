package com.cons.consumer.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumerService {

    @JmsListener(destination="channel1")
    public void listener(String message) {
        String sender = message.substring(0, message.indexOf("|"));
        System.out.println("\nMessage from: "+sender+"\n--------------------------------");
        System.out.println(message.substring(message.indexOf("|")+1));
    }
}
