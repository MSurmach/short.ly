package com.example.shortener.service.impl;

import com.example.shortener.exception.AuthorityIsNotExisted;
import com.example.shortener.model.Authority;
import com.example.shortener.repository.AuthorityRepository;
import com.example.shortener.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    private AuthorityRepository authorityRepository;

    @Autowired
    public void setAuthorityRepository(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Authority getAuthorityByName(String authorityName) {
        Optional<Authority> authority = authorityRepository.getAuthorityByName(authorityName);
        return authority.orElseThrow(() -> new AuthorityIsNotExisted("Authority isn't existed"));
    }
}
