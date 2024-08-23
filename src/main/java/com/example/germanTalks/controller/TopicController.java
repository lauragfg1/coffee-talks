package com.example.germanTalks.controller;

import java.util.List;
import com.example.germanTalks.model.Topic;
import com.example.germanTalks.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

     @GetMapping("/getVideoUrlByTopic/{topicId}")
     public String getVideoUrlByTopic(@PathVariable Integer topicId) {
          String videoUrl = topicService.getVideoUrlByTopicId(topicId);
          if (videoUrl != null) {
               return videoUrl;
          } else {
               return "Not found";
          }
     }
}
