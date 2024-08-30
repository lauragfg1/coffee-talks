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

    @Override
    public String getVideoUrlByTopicId(Integer topicId) {
        return topicRepository.findById(topicId)
                .map(Topic::getVideo)
                .orElse(null);
    }

    @Override
    public Topic findByName(String topicName) {
        return topicRepository.findByName(topicName);
    }
}
