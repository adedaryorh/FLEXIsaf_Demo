package com.example.Firstdemo.controller;

import com.example.Firstdemo.exception.UserNotFoundException;
import com.example.Firstdemo.model.User;
import com.example.Firstdemo.exception.UserException;
import com.example.Firstdemo.exception.ResourceNotFoundException;
import com.example.Firstdemo.exception.GlobalExceptions;
import com.example.Firstdemo.repository.UserRepository;
import com.example.Firstdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Controller
@RestController
@RequestMapping("/users")
public class UserController {

   // @Autowired
    //UserService userService;
    @Autowired
    UserRepository userRepository;

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;}


    @GetMapping("/errors")
    public ResponseEntity<String> notFoundHandler(){
        return ResponseEntity.badRequest().body(new GlobalExceptions().
                handleResourceNotFound(new ResourceNotFoundException()))
                .getBody();
    }
    @GetMapping("/h")
    public ResponseEntity<String> errorUnknowns(HttpStatus httpStatus){
        if(HttpStatus.INTERNAL_SERVER_ERROR.is5xxServerError()){
            return ResponseEntity.badRequest().body(new GlobalExceptions()
                            .handleExceptions(new Exception()))
                    .getBody();
        }
        return ResponseEntity.internalServerError().body(new GlobalExceptions()
                        .handleResourceNotFound(new ResourceNotFoundException()))
                .getBody();

    }

    @GetMapping("/")
    public ResponseEntity<String> error(HttpStatus httpStatus){
        if(HttpStatus.NOT_FOUND.is4xxClientError()){
            return ResponseEntity.badRequest().body(new GlobalExceptions()
                            .handleResourceNotFound(new ResourceNotFoundException()))
                    .getBody();
        }
        return ResponseEntity.internalServerError().body(new GlobalExceptions()
                        .handleResourceNotFound(new ResourceNotFoundException()))
                .getBody();

    }

    @GetMapping(value = "all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(()-> new UserException("User with email not found"));
            return ResponseEntity.ok(user);
        }catch (Exception e){
            throw e;
        }
    }

    @PostMapping("newUser")
    public ResponseEntity<User> createUser(@RequestBody User userRequest) {
        User user = userService.saveUser(userRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("userId") Long userId) {
        System.out.println("GET REQUEST...");
        try {
            Optional<User> user = userService.getUserById(userId);
            if (user.isPresent()) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound()
                        .build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("updateUser/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId") Long userId,
                                             @RequestBody User user) {
        System.out.println("UPDATE REQUEST...");
        try {
            User user1 = userRepository.findById(userId).orElseThrow(()-> new UserException("user not found"));
            user1.setEmail(user.getEmail());
            user1.setFirstName(user.getFirstName());
            user1.setLastName(user.getLastName());
            user1.setAddress(user.getAddress());
            userRepository.save(user1);
            //userService.updateUser(userId, user);
            return ResponseEntity.ok("User Info updated successfully");

        }catch (Exception e){
            throw e;
        }

    }


    @DeleteMapping(path = "deleteAUser/{userId}")
    public ResponseEntity<String> deleteAUser(@PathVariable("userId") Long userId) {
        System.out.println("DELETE REQUEST FOR CUSTOMER WITH ID " + userId);
        try {
            userService.deleteUserById(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("An error occurred: " + e
                            .getMessage());
        }
    }

}
