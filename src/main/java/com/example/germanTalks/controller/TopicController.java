package com.example.germanTalks.controller;

import java.util.List;
import com.example.germanTalks.model.Topic;
import com.example.germanTalks.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
@CrossOrigin(origins = "http://localhost:3000")
public class TopicController {

     @Autowired
     private TopicService topicService;

     @GetMapping("/getAll")
     public List<Topic> getAllTopics() {
         return topicService.getAllTopics();
     }
}
