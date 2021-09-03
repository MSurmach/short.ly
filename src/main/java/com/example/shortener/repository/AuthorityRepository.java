package com.example.shortener.repository;

import com.example.shortener.model.Authority;

import java.util.Optional;

public interface AuthorityRepository {
    Optional<Authority> getAuthorityByName(String authorityName);
}
