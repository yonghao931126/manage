package com.haojie.manage.service;

import com.haojie.manage.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll();

    void save(Permission permission);

    Permission findById(String id);

    void deleteById(String id);
}
