package com.example.germanTalks.repository;

import com.example.germanTalks.model.Talk;
import com.example.germanTalks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkRepository extends JpaRepository<Talk, Integer> {
    List<Talk> findByUser1(User user);
}
