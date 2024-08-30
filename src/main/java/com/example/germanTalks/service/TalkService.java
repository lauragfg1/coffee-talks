package com.example.germanTalks.service;

import com.example.germanTalks.model.Talk;
import com.example.germanTalks.model.User;

import java.util.List;


public interface TalkService {
    Talk saveTalk(Talk talk);
    List<Talk> findAllTalks(User user);
}

