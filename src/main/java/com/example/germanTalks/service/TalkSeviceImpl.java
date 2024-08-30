package com.example.germanTalks.service;

import com.example.germanTalks.model.Talk;
import com.example.germanTalks.model.User;
import com.example.germanTalks.repository.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalkSeviceImpl implements TalkService {

    @Autowired
    private TalkRepository talkRepository;

    @Override
    public Talk saveTalk(Talk talk) {
        return talkRepository.save(talk);
    }

    @Override
    public List<Talk> findAllTalks(User user) {
        return talkRepository.findByUser1(user);
    }
}
