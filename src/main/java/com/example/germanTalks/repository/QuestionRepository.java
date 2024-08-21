package com.example.germanTalks.repository;

import com.example.germanTalks.model.Language;
import com.example.germanTalks.model.Question;
import com.example.germanTalks.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByTopic(Topic topic);
}
