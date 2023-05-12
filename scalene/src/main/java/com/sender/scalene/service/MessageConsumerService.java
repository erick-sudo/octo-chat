package com.sender.scalene.service;

import com.sender.scalene.models.Channel;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumerService {

    private Long channelId = 0L;

    @JmsListener(destination="channel")
    public void listener(String message) {
        String sender = message.substring(0, message.indexOf("|"));
        System.out.println("\nMessage from: "+sender+"\n--------------------------------");
        System.out.println(message.substring(message.indexOf("|")+1));
    }

    public void setChannel(Channel channel) {
        this.channelId = channel.getId();
    }
}
