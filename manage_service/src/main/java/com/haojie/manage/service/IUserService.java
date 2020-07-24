package com.haojie.manage.service;

import com.haojie.manage.domain.Role;
import com.haojie.manage.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {



    List<UserInfo> findAll();

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findOtherRoles(String id);

    void addRoleToUser(String userId, String[] roleIds);
}
