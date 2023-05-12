package com.sender.scalene.controllers;

import com.sender.scalene.repos.ChannelRepository;
import com.sender.scalene.repos.MessageRepository;
import com.sender.scalene.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChatController {

    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private ChannelRepository channelRepository;

    @GetMapping("/")
    public String index() {
        return "chat";
    }

    @GetMapping("/chat/{channel}")
    public String chat(@PathVariable String channel) {
        return "chat";
    }

//    @PostMapping("/chat")
//    public ResponseEntity<String> sendMessage(@RequestBody Message requestData ) {
//        // Service Class
//        sendService.sendMessage(requestData+"");
//        return ResponseEntity.ok("message sent successfully");
//    }
}
