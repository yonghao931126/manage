package com.haojie.manage.service.impl;

import com.haojie.manage.dao.IUserDao;
import com.haojie.manage.domain.Role;
import com.haojie.manage.domain.UserInfo;
import com.haojie.manage.service.IUserService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    //配置加密
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(username);
        List<Role> roles = userInfo.getRoles();

        List<SimpleGrantedAuthority> authoritys = getAuthority(roles);

        User user = new User(userInfo.getUsername(), userInfo.getPassword(),
                userInfo.getStatus() == 0 ? false : true,
                true, true, true, authoritys);
        System.out.println(user.getPassword());
        return user;
    }

    /**
     * 作用就是返回一个集合,里面装的是用户权限
     *
     * @return
     */
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        ArrayList<SimpleGrantedAuthority> authoritys = new ArrayList<>();
        for (Role role : roles) {

            authoritys.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authoritys;
    }

    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    /**
     *
     * @param userInfo
     */
    @Override
    public void save(UserInfo userInfo) {
        //加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    /**
     * 根据id查询详情
     * @param id
     */
    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    //查询用户可以添加的其他role
    @Override
    public List<Role> findOtherRoles(String id) {
        return userDao.findOtherRoles(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId : roleIds){
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
