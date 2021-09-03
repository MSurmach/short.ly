package com.example.shortener.controller;

import com.example.shortener.exception.UserIsExistException;
import com.example.shortener.model.User;
import com.example.shortener.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralController.class);
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signUser(@RequestBody User user) {
        try {
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserIsExistException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   /* @DeleteMapping(value = "/deleteUser/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        userService.loadUserByUsername(username);
        if (userService.deleteUserByUserName(username)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/updateUser/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        if (userService.updateUser(user)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/
}
