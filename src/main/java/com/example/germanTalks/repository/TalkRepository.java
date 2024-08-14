package com.example.germanTalks.repository;

import com.example.germanTalks.model.Talk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalkRepository extends JpaRepository<Talk, Integer> {
}
