package com.haojie.manage.service.impl;

import com.haojie.manage.dao.IRoleDao;
import com.haojie.manage.domain.Permission;
import com.haojie.manage.domain.Role;
import com.haojie.manage.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void deleteRoleByid(String id) {
        //users_role表中删除
        roleDao.deleteFromUsers_RoleById(id);
        //role_permission表中删除
        roleDao.deleteFromRole_PermissionById(id);
        //role表中删除
        roleDao.deleteRoleById(id);


    }

    @Override
    public List<Permission> findOtherPermission(String id) {
        return roleDao.findOtherPermission(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId:permissionIds){
            roleDao.addPermissionToRole(permissionId,roleId);
        }
    }
}
