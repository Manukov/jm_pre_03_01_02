package com.manukov.service;

import com.manukov.dao.RoleDao;
import com.manukov.entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }

    @Transactional
    @Override
    public Role getRoleById(long id) {
        return roleDao.getRoleById(id);
    }

    @Transactional
    @Override
    public Set<Role> getRolesById(long[] rolesId) {

        Set<Role> roles = new HashSet<>();

        for (int i=0; i<rolesId.length; i++) {
            roles.add(roleDao.getRoleById(rolesId[i]));
        }
        return roles;
    }

}
