package com.example.Firstdemo.repository;

import com.example.Firstdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //custom query
    Optional<User> findByEmail(String email);




}
