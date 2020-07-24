package com.haojie.manage.controller;


import com.github.pagehelper.PageInfo;
import com.haojie.manage.domain.Orders;
import com.haojie.manage.domain.Product;
import com.haojie.manage.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    /**
     * 分页查询订单
     *
     * @return
     */
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page, @RequestParam(value = "size", required = true, defaultValue = "4") Integer size) {
        List<Orders> ordersList = ordersService.findAll(page, size);
        ModelAndView mv = new ModelAndView();
        //pageinfo就是分页bean
        PageInfo<Orders> pageInfo = new PageInfo<Orders>(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }


    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(value = "id", required = true) String id) {
        Orders orders=ordersService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
