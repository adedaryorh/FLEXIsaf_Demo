package com.example.Firstdemo.service;


import com.example.Firstdemo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(Long userId);

    void updateUser(Long userId, User user);

    void deleteUserById(Long userId);
}
