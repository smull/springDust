package com.levelup.spring.service;


import com.levelup.stock.model.User;

import java.util.List;

public interface UserService {

    public User getById(Long id);

    public User create(User user);

    public User update(User user);

    public Boolean delete(Long id);

    public List<User> getUserByEmail(String email);

    public User getUserByEmailAndPassword(String email, String password);

    public Boolean checkUserByEmail(User user);

    public Boolean checkUserByEmailAndPassword(User user);

}
