package com.example.germanTalks.controller;

import com.example.germanTalks.model.Talk;
import com.example.germanTalks.model.Topic;
import com.example.germanTalks.model.User;
import com.example.germanTalks.service.TalkService;
import com.example.germanTalks.service.TopicService;
import com.example.germanTalks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/talks")
@CrossOrigin(origins = "http://localhost:3000")
public class TalkController {

    @Autowired
    private TalkService talkService;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @PostMapping("/create")
    public Talk createTalk(@RequestParam String date, @RequestParam String time, @RequestParam String topicName, @RequestParam String userName, @AuthenticationPrincipal OidcUser principal) {

        User user2 = userService.findByName(userName);
        Topic topic = topicService.findByName(topicName);
        Instant dateTime = Instant.parse(date + "T" + time + ":00Z");

        Talk talk = new Talk(dateTime, principal, user2, topic);

        return talkService.saveTalk(talk);
    }


    @GetMapping("/talksFrom")
    public List<Talk> getTalksFrom(@RequestParam String userName) {
        User user = userService.findByName(userName);
        return talkService.findAllTalks(user);
    }
}