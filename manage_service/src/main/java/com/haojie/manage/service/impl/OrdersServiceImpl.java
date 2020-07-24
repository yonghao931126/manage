package com.haojie.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.haojie.manage.dao.IOrdersDao;
import com.haojie.manage.domain.Orders;
import com.haojie.manage.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {


    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) {
        //分页
        PageHelper.startPage(1,4);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String id) {
        return ordersDao.findById(id);
    }
}
