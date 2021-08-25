package com.manukov.dao;

import com.manukov.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    private final RoleDao roleDao;

    public UserDaoImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public User findByUsername(String username) {
            return this.findByEmail(username);
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user WHERE user.email=:email", User.class);
        User user = query.setParameter("email", email).getSingleResult();
        return user;
    }

    @Override
    public User findById(long id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user WHERE user.id=:id", User.class);
        User user = query.setParameter("id", id).getSingleResult();
        return user;
    }

    @Override
    public List<User> getUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public boolean addUser(User user) {
        try {
            entityManager.persist(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(long id) {
        try {
            entityManager.createQuery("DELETE FROM User WHERE id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            entityManager.persist(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
