package com.example.germanTalks.service;

import com.example.germanTalks.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    boolean userExistsByEmail(String email);

    String findByEmail(String email);

    void saveUser(User user);
}
