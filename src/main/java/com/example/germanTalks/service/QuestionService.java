package com.example.germanTalks.service;

import com.example.germanTalks.model.Question;
import com.example.germanTalks.model.Topic;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions(Topic topic);
}
