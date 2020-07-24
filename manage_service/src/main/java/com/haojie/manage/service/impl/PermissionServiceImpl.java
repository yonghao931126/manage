package com.haojie.manage.service.impl;

import com.haojie.manage.dao.IPermissionDao;
import com.haojie.manage.domain.Permission;
import com.haojie.manage.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String id) {
        return permissionDao.find(id);
    }

    @Override
    public void deleteById(String id) {
        //删除Role_permission表
        permissionDao.deleteFromRole_PermissionById(id);
        //删除permission中信息
        permissionDao.deleteById(id);
    }
}
