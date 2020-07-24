package com.haojie.manage.service;

import com.haojie.manage.domain.Permission;
import com.haojie.manage.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    void save(Role role);

    Role findById(String id);

    void deleteRoleByid(String id);

    List<Permission> findOtherPermission(String id);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
