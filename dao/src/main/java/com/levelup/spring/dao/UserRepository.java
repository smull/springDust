package com.levelup.spring.dao;

import com.levelup.stock.model.User;

import java.util.List;

public interface UserRepository {

    public User getById(Long id, Class entityClass);

    public User create(User user);

    public User update(User user);

    public Boolean delete(Long id, Class entityClass);

    public List<User> getUserByEmail(String email);

    public User getUserByEmailAndPassword(String email, String password);

    public Boolean checkUserByEmail(User user);

    public Boolean checkUserByEmailAndPassword(User user);

    public User getUserById(Long id);
}
