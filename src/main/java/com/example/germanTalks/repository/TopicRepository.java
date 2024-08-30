package com.example.germanTalks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.germanTalks.model.Topic;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    Optional<Topic> findById(Integer id);

    Topic findByName(String name);
}
