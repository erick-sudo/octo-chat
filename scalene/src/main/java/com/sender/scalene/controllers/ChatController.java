package com.sender.scalene.controllers;

import com.sender.scalene.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RestController;
import com.sender.scalene.service.SendService;

@Controller
public class ChatController {

    @Autowired
    private SendService sendService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Message requestData ) {
        // Service Class
        sendService.sendMessage(requestData+"");
        return ResponseEntity.ok("message sent successfully");
    }
}