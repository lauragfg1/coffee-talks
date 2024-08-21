package com.example.germanTalks.controller;

import com.example.germanTalks.model.Question;
import com.example.germanTalks.model.Topic;
import com.example.germanTalks.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/getByTopic/{topicId}")
    public List<Question> getQuestionsByTopic(@PathVariable Integer topicId) {
        Topic topic = new Topic();
        topic.setId(topicId);
        return questionService.getAllQuestions(topic);
    }
}