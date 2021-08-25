package com.manukov.dao;

import com.manukov.entity.User;

import java.util.List;


public interface UserDao {

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> getUsers();

    boolean addUser(User user);

    User findById(long id);

    boolean deleteUser(long id);

    boolean updateUser(User user);
}
