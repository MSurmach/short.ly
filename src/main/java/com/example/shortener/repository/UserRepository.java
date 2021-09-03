package com.example.shortener.repository;

import com.example.shortener.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User> {
    Optional<User> findUserByUsername(String username);
}
