package com.example.germanTalks.service;

import com.example.germanTalks.model.Topic;
import com.example.germanTalks.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }
}
