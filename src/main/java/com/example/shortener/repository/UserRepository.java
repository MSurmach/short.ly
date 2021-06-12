package com.example.shortener.repository;

import com.example.shortener.model.User;

public interface UserRepository {
    User findUserByLogin(String login);

    void persist(User user);
}
