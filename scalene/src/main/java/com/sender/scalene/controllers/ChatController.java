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

@Controller
public class ChatController {

    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private ChannelRepository channelRepository;

    // Active MQ services
    private MessageSendService sendService;
    private MessageConsumerService consumerService;

    public Long loggedInUser = 1L;

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
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/chat/{sender}/{receiver}")
    public String chat(@PathVariable Long sender, @PathVariable Long receiver, Model model) {
        loggedInUser = sender;
        List<User> users = userRepository.findAll();
        List<Message> messages = messageRepository.findMessages(sender, receiver);
        User selecteduser = userRepository.findUser(receiver);
        User log = userRepository.findUser(sender);

        model.addAttribute("selecteduser", selecteduser);
        model.addAttribute("users", users);
        model.addAttribute("log", log);
        model.addAttribute("messages", messages);
        return "chat";
    }
}
