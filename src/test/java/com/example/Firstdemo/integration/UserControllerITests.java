package com.example.Firstdemo.integration;

import com.example.Firstdemo.model.User;
import com.example.Firstdemo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerITests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        userRepository.deleteAll();
    }

    @Test
    public void givenUserObject_whenCreateUser_thenReturnSaveUser() throws Exception{

        //given - precondition or setup
        User user = User.builder()
                .firstName("ex")
                .lastName("yahooboi")
                .email("hvictoraxu@gmail.com")
                .gender("male")
                .password("secretpassword")
                .dateOfBirth("1990-07-15")
                .address("123 odeku St")
                .stateOfOrigin("lagos")
                .accountNumber ("0938028926")
                .phoneNumber("553-792-5995")
                .status("Active")
                .build();

        //when action or behaviour that we are going to test
        ResultActions response = mockMvc.perform(post("/users/newUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        //then verify the result or output using asser statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(user.getLastName())))
                .andExpect(jsonPath("$.email",
                        is(user.getEmail())))
                .andExpect(jsonPath("$.gender",
                        is(user.getGender())))
                .andExpect(jsonPath("$.password",
                        is(user.getPassword())))
                .andExpect(jsonPath("$.dateOfBirth",
                        is(user.getDateOfBirth())))
                .andExpect(jsonPath("$.address",
                        is(user.getAddress())))
                .andExpect(jsonPath("$.stateOfOrigin",
                        is(user.getStateOfOrigin())))
                .andExpect(jsonPath("$.accountNumber",
                        is(user.getAccountNumber())))
                .andExpect(jsonPath("$.phoneNumber",
                        is(user.getPhoneNumber())))
                .andExpect(jsonPath("$.status",
                        is(user.getStatus())));
    }

     //JUnit test for getting All Users RESTAPI
         @Test
         public void givenListOfUsers_whenGetAllUsers_thenReturnUsersList() throws Exception{
             //given - preconditioned or setup
             List<User> listOfUsers = new ArrayList<>();
             listOfUsers.add(User.builder().firstName("Tunde").lastName("sadiq")
                     .email("tunde@gmail.com").gender("Male").password("secret")
                     .dateOfBirth("1991-07-15").address("No 13 osl").stateOfOrigin("Osun")
                     .accountNumber("0160875334").phoneNumber("08093537393").status("ACTIVE")
                     .build());
             listOfUsers.add(User.builder().firstName("Tundex").lastName("sadiqx")
                     .email("tundex@gmail.com").gender("Male").password("secrete")
                     .dateOfBirth("1999-07-18").address("No 10 osl").stateOfOrigin("Oyo")
                     .accountNumber("0160899334").phoneNumber("08005537393").status("ACTIVE")
                     .build());
             userRepository.saveAll(listOfUsers);
             //when - action or behaviour we need to test
             ResultActions response = mockMvc.perform(get("/users/all"));
             //then - verify the output
             response.andExpect(status().isOk())
                     .andDo(print())
                     .andExpect(jsonPath("$.size()",
                             is(listOfUsers.size())));
         }
}

