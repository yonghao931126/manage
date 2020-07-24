package com.haojie.manage.dao;

import com.haojie.manage.domain.Role;
import com.haojie.manage.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {


    UserInfo findById(String id);


    UserInfo findByUsername(String username);


    List<UserInfo> findAll();

    void save(UserInfo userInfo);

    List<Role> findOtherRoles(String id);

    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId);
}
