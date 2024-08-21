package com.example.germanTalks.service;

import com.example.germanTalks.model.Question;
import com.example.germanTalks.model.Topic;
import com.example.germanTalks.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionSeviceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestions(Topic topic) {
        return questionRepository.findByTopic(topic);
    }
}
