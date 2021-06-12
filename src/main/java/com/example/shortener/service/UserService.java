package com.example.shortener.service;

import com.example.shortener.exception.UserNotFoundException;
import com.example.shortener.model.User;

public interface UserService {
    void saveUser(User user);

    User findUserByLogin(String login);

    void loginUser(User user) throws UserNotFoundException;
}
