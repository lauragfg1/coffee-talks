package com.example.germanTalks.service;

import com.example.germanTalks.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    boolean userExistsByEmail(String email);

    User findByEmail(String email);

    void saveUser(User user);

    List<User> getAllParticipants();

    User findByName(String name);
}
