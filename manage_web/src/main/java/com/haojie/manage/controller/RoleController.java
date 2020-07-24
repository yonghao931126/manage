package com.haojie.manage.controller;

import com.haojie.manage.domain.Permission;
import com.haojie.manage.domain.Role;
import com.haojie.manage.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;


    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    //角色详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    //删除角色
    @RequestMapping("/deleteRole.do")
    public String delete(String id) {
        roleService.deleteRoleByid(id);
        return "redirect:findAll.do";
    }

    //添加权限
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam("id") String id) {
        //根据roleId查询role
        Role role = roleService.findById(id);
        //根据roleId查询可以添加的权限
        List<Permission> otherPermission=roleService.findOtherPermission(id);
        ModelAndView mv  = new ModelAndView();
        mv.addObject("role",role);
        mv.addObject("permissionList",otherPermission);

        mv.setViewName("role-permission-add");

        return mv;
    }

    @RequestMapping("/addPermissionToRole")   //roleId为jsp页面隐藏项的id    <input type="hidden" name="roleId" value="${role.id}">
    //<input name="ids" type="checkbox" value="${role.id}">
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId, @RequestParam(name = "ids", required = true) String[] permissionIds) {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }
}
