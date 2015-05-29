package com.levelup.spring.service.impl;


import com.levelup.spring.dao.UserRepository;
import com.levelup.spring.service.UserService;
import com.levelup.stock.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    public UserServiceImpl() {
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id, User.class);
    }

    @Override
    public User create(User user) {

        user.setEmail(user.getEmail().toLowerCase());
        return userRepository.create(user);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public Boolean delete(Long id) {
        return userRepository.delete(id,User.class);
    }

    @Override
    public List<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.getUserByEmailAndPassword(email, password);
    }

    @Override
    public Boolean checkUserByEmail(User user) {
        return userRepository.checkUserByEmail(user);
    }

    @Override
    public Boolean checkUserByEmailAndPassword(User user) {
        return userRepository.checkUserByEmailAndPassword(user);
    }
}
