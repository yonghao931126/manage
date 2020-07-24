package com.haojie.manage.service;

import com.haojie.manage.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll(int page,int size);

    Orders findById(String id);
}
