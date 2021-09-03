package com.example.shortener.service.impl;

import com.example.shortener.exception.UserIsExistException;
import com.example.shortener.exception.UserIsNotExistException;
import com.example.shortener.model.CustomUserDetails;
import com.example.shortener.model.User;
import com.example.shortener.repository.UserRepository;
import com.example.shortener.service.AuthorityService;
import com.example.shortener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private AuthorityService authorityService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> loadedUser = userRepository.findUserByUsername(username);
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUser(loadedUser.orElseThrow(() -> new UsernameNotFoundException("The user is not found")));
        return userDetails;
    }

    @Override
    @Transactional
    public void save(User user) {
        try {
            loadUserByUsername(user.getUsername());
            throw new UserIsExistException("Unable to save. The user is already existed");
        } catch (UsernameNotFoundException exception) {
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
            user.getAuthorities().add(authorityService.getAuthorityByName("USER"));
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void delete(String username) {
        try {
            User deletable = loadUserByUsername(username).getUser();
            userRepository.delete(deletable);
        } catch (UsernameNotFoundException exception) {
            throw new UserIsNotExistException("Unable to delete. The user doesn't exist");
        }
    }

    @Override
    @Transactional
    public void update(String username) {
        try {
            User deletable = loadUserByUsername(username).getUser();
            userRepository.update(deletable);
        } catch (UsernameNotFoundException exception) {
            throw new UserIsNotExistException("Unable to update. The user doesn't exist");
        }
    }
}
