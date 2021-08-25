package com.manukov.dao;

import com.manukov.entity.Role;

import java.util.List;

;

public interface RoleDao {
    List<Role> getRoles();

    Role getRoleById(long id);
}
