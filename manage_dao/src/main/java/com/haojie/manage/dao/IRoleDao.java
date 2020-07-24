package com.haojie.manage.dao;

import com.haojie.manage.domain.Permission;
import com.haojie.manage.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleDao {


    List<Role> findRoleByUserId(String userId);

    List<Role> findAll();

    void save(Role role);

    Role findById(String id);

    void deleteRoleById(String id);

    void deleteFromRole_PermissionById(String id);

    void deleteFromUsers_RoleById(String id);

    List<Permission> findOtherPermission(String id);

    void addPermissionToRole(@Param("permissionId") String permissionId,@Param("roleId") String roleId);
}
