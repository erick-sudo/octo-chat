package com.sender.scalene.service;

import com.sender.scalene.models.Channel;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumerService {

    private Channel channel;

    @JmsListener(destination="channel${channel.getId()}")
    public void listener(String message) {
        String sender = message.substring(0, message.indexOf("|"));
        System.out.println("\nMessage from: "+sender+"\n--------------------------------");
        System.out.println(message.substring(message.indexOf("|")+1));
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
