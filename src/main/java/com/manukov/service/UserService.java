package com.manukov.service;

import com.manukov.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    boolean addUser(User user, String[] rolesId);
    User findById(long id);
    boolean updateUser(User user, String[] rolesId);
    boolean deleteUser(long id);
}
