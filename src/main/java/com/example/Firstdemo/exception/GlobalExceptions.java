package com.example.Firstdemo.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptions {

    @Autowired
    ResourceNotFoundException resourceNotFoundException;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions( Exception exception ){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("There has been an internal server error");
    }
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found: " + ex.getMessage());
    }

    @ExceptionHandler( ResourceNotFoundException.class )
    public ResponseEntity<String> handleResourceNotFound( ResourceNotFoundException resourceNotFoundException ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource could not be found");
    }

}