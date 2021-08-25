package com.manukov.dao;

import com.manukov.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRoles() {
        TypedQuery<Role> query = entityManager.createQuery("from Role", Role.class);
        List<Role> roles = query.getResultList();
        return roles;
    }

    @Override
    public Role getRoleById(long id) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT role FROM Role role WHERE role.id=:id", Role.class);
        Role role =query.setParameter("id", id).getSingleResult();
        return role;
    }
}
