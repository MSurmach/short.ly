package com.example.shortener.service;

import com.example.shortener.model.CustomUserDetails;
import com.example.shortener.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    void save(User user);

    void delete(String username);

    void update(String username);

    @Override
    CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
