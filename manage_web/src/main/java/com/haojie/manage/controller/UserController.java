package com.haojie.manage.controller;


import com.haojie.manage.domain.Role;
import com.haojie.manage.domain.UserInfo;
import com.haojie.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService uesrService;


    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll() {
        List<UserInfo> userList = uesrService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username=='tom'") //只有tom用户才可以执行添加操作
    public String save(UserInfo userInfo) {
        uesrService.save(userInfo);
        return "redirect:findAll.do";
    }

    //用户详情根据id查询
    @RequestMapping("findById.do")
    public ModelAndView findById(String id) {
        ModelAndView mv = new ModelAndView();
        UserInfo user = uesrService.findById(id);
        mv.addObject("user", user);
        mv.setViewName("user-show");
        return mv;
    }

    //查询用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) {
        //根据用户id查询用户
        UserInfo userInfo = uesrService.findById(id);
        //根据用户id查询可以添加的角色
        List<Role> otherRoles = uesrService.findOtherRoles(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", userInfo);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")   //userId为jsp页面隐藏项的id    <input type="hidden" name="userId" value="${user.id}">
    //<input name="ids" type="checkbox" value="${role.id}">
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleIds) {
        uesrService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }
}
