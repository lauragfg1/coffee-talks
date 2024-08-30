package com.example.germanTalks.service;

import com.example.germanTalks.model.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    List<Topic> getAllTopics();
    String getVideoUrlByTopicId(Integer topicId);

    Topic findByName(String topicName);
}
