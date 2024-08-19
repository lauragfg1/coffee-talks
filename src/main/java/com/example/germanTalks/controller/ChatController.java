package com.example.germanTalks.controller;

import com.example.germanTalks.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping("/send")
    public String getChatGPTResponse(@RequestBody String prompt) {
        return chatGPTService.getChatGPTResponse(prompt);
    }
}
