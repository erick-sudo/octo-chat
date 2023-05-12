package com.sender.scalene.controllers;

import com.sender.scalene.repos.ChannelRepository;
import com.sender.scalene.repos.MessageRepository;
import com.sender.scalene.repos.UserRepository;
import com.sender.scalene.service.MessageConsumerService;
import com.sender.scalene.service.MessageSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChatController {

    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private ChannelRepository channelRepository;

    // Active MQ services
    private MessageSendService sendService;
    private MessageConsumerService consumerService;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Autowired
    public MessageRepository voidGetMessageRepository() {
        return this.messageRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setChannelRepository(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @GetMapping("/")
    public String index() {
        return "chat";
    }

    @GetMapping("/chat/{channel}")
    public String chat(@PathVariable Long channel) {
        System.out.println(channel);
        return "chat";
    }
}
