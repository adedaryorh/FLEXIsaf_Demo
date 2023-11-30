package com.example.Firstdemo.unit.repository;

import com.example.Firstdemo.model.User;
import com.example.Firstdemo.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    //JUNIT test for save user operation
    @DisplayName("JUNIT test for save user operation")
    @Test
    public void givenUserObject_whenSave_thenReturnSavedUser(){

        //given - preconditioned or setup
        User user = User.builder()
                .firstName("Hakim")
                .lastName("Victor")
                .email("hvictora@gmail.com")
                .gender("male")
                .password("secretpassword")
                .dateOfBirth("1990-07-15")
                .address("123 odeku St")
                .stateOfOrigin("lagos")
                .accountNumber ("0138428926")
                .phoneNumber("553-790-5555")
                .status("Active")
                .build();

        //when - action or behaviour we need to test
        User savedUser = userRepository.save(user);

        //then - verify the output
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    //JUnit test for get All users operations
    @DisplayName("JUnit test for get All users operations")
    @Test
    public void givenUserList_whenFindAll_thenUserList(){
        //given - preconditioned or setup

        User user = User.builder()
                .firstName("Hakimi")
                .lastName("Victor")
                .email("hvictoria@gmail.com")
                .gender("male")
                .password("secretpassword")
                .dateOfBirth("1992-07-15")
                .address("123 odeku St")
                .stateOfOrigin("lagos")
                .accountNumber ("0118428926")
                .phoneNumber("557-790-5555")
                .status("Active")
                .build();

        User user1 = User.builder()
                .firstName("Hakimas")
                .lastName("Victor")
                .email("hvictorasa@gmail.com")
                .gender("male")
                .password("secretpassword")
                .dateOfBirth("1999-07-25")
                .address("123 odeku St")
                .stateOfOrigin("lagos")
                .accountNumber ("0838428926")
                .phoneNumber("559-790-5555")
                .status("Active")
                .build();

        userRepository.save(user);
        userRepository.save(user1);

        //when - action or behaviour we need to test
        List<User> userList = userRepository.findAll();

        //then - verify the output
        Assertions.assertThat(userList).isNotNull();
        Assertions.assertThat(userList.size()).isEqualTo(2);
    }

     //JUnit test for get user by ID
    @DisplayName("JUnit test for get user by ID")
    @Test
    public void givenUserObject_whenFindById_thenReturnUserObject(){
             //given - preconditioned or setup
             User user = User.builder()
                     .firstName("Hakim")
                     .lastName("Victor")
                     .email("hvictora@gmail.com")
                     .gender("male")
                     .password("secretpassword")
                     .dateOfBirth("1990-07-15")
                     .address("123 odeku St")
                     .stateOfOrigin("lagos")
                     .accountNumber ("0138428926")
                     .phoneNumber("553-790-5555")
                     .status("Active")
                     .build();
             userRepository.save(user);
             //when - action or behaviour we need to test
             User userDB = userRepository.findById(user.getId()).get();
             //then - verify the output
             Assertions.assertThat(userDB).isNotNull();
    }

        //JUnit test for get user by email
        @DisplayName("JUnit test for get user by email")
        @Test
         public void givenUserEmail_whenFindByEmail_thenReturnUserObject(){
             //given - preconditioned or setup
            User user = User.builder()
                    .firstName("Hakim")
                    .lastName("Victor")
                    .email("hvictora@gmail.com")
                    .gender("male")
                    .password("secretpassword")
                    .dateOfBirth("1990-07-15")
                    .address("123 odeku St")
                    .stateOfOrigin("lagos")
                    .accountNumber ("0138428926")
                    .phoneNumber("553-790-5555")
                    .status("Active")
                    .build();
            userRepository.save(user);
             //when - action or behaviour we need to test
            User userDB = userRepository.findByEmail(user.getEmail()).get();
             //then - verify the output
            Assertions.assertThat(userDB).isNotNull();
         }
}
