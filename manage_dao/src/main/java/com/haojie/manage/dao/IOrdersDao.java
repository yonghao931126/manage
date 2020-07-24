package com.haojie.manage.dao;

import com.haojie.manage.domain.Orders;

import java.util.List;

public interface IOrdersDao {


    List<Orders> findAll();

    Orders findById(String id);
}
