package com.example.shortener.service.impl;

import com.example.shortener.exception.UserNotFoundException;
import com.example.shortener.model.User;
import com.example.shortener.repository.UserRepository;
import com.example.shortener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void loginUser(User user) throws UserNotFoundException {
        String login = user.getLogin();
        User foundUser = findUserByLogin(login);
        if (foundUser == null || !foundUser.getPassword().equals(user.getPassword())) {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackFor = DataIntegrityViolationException.class)
    public void saveUser(User user) {
        userRepository.persist(user);
    }

    @Override
    @Transactional
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }


}
