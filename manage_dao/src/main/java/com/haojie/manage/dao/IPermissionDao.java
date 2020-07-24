package com.haojie.manage.dao;

import com.haojie.manage.domain.Permission;

import java.util.List;

public interface IPermissionDao {

    //根据roleid查询所拥有的权限
    List<Permission> findById(String id);

    List<Permission> findAll();

    void save(Permission permission);

    Permission find(String id);

    void deleteFromRole_PermissionById(String id);

    void deleteById(String id);
}
