package com.manukov.service;

import com.manukov.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getRoles();
    Role getRoleById(long id);
    Set<Role> getRolesById(long[] rolesId);
}
