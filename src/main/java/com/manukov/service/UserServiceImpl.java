package com.manukov.service;

import com.manukov.dao.RoleDao;
import com.manukov.dao.UserDao;
import com.manukov.entity.Role;
import com.manukov.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final RoleService roleService;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.roleService = roleService;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //userDao.findByEmail(s);

        User user = userDao.findByUsername(s);
        if(user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return user;
    }

    @Transactional
    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Transactional
    @Override
    public boolean addUser(User user, String[] rolesId) {
        user.setRoles(getRolesByArrayId(rolesId));
        return userDao.addUser(user);
    }

    @Transactional
    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Transactional
    @Override
    public boolean updateUser(User user, String[] rolesId) {

        User newUser = userDao.findById(user.getId());
        newUser.setEmail(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setRoles(getRolesByArrayId(rolesId));

        return userDao.updateUser(newUser);
    }

    @Transactional
    @Override
    public boolean deleteUser(long id) {
        return userDao.deleteUser(id);
    }

    private Set<Role> getRolesByArrayId(String[] rolesId) {
        Set<Role> roles = new HashSet<>();
        for(String id: rolesId){
            System.out.println(id);
            roles.add(roleService.getRoleById(Long.parseLong(id)));
        }
        return roles;
    }

}
