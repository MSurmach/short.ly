package com.example.shortener.controller;

import com.example.shortener.exception.UserNotFoundException;
import com.example.shortener.jsonViews.UserView;
import com.example.shortener.model.User;
import com.example.shortener.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @JsonView(UserView.UserLoginView.class)
    @PostMapping(value = "/signUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> signUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (DataIntegrityViolationException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @JsonView(UserView.UserLoginView.class)
    @PostMapping(value = "/loginUser")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        try {
            userService.loginUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
