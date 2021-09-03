package com.example.shortener.service;

import com.example.shortener.model.Authority;

public interface AuthorityService {
    Authority getAuthorityByName(String authorityName);
}
