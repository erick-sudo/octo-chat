package com.sender.scalene.controllers;

import com.sender.scalene.models.Message;
import com.sender.scalene.models.User;
import com.sender.scalene.repos.ChannelRepository;
import com.sender.scalene.repos.MessageRepository;
import com.sender.scalene.repos.UserRepository;
import com.sender.scalene.service.MessageConsumerService;
import com.sender.scalene.service.MessageSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setChannelRepository(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<User> users = userRepository.findAll();
        List<Message> messages = messageRepository.findAll();

        model.addAttribute("users", users);
        model.addAttribute("messages", messages);
        return "chat";
    }

    @GetMapping("/chat/{id}")
    public String chat(@PathVariable Long id) {
        List<User> users = userRepository.findAll();
        List<Message> messages = messageRepository.findMessagesBy();
        Optional<User> selecteduser = userRepository.findById(id);
        return "chat";
    }
}
