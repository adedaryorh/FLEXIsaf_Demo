package com.example.Firstdemo.service.impl;

import com.example.Firstdemo.model.User;
import com.example.Firstdemo.repository.UserRepository;
import com.example.Firstdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void updateUser(Long userId, User user) {
         userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteAllById(Collections.singleton(userId));

    }



}
