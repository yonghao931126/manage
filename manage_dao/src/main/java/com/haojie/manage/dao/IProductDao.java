package com.haojie.manage.dao;

import com.haojie.manage.domain.Product;

import java.util.List;

public interface IProductDao {

    /**
     * 查询所有商品
     * @return
     */
    List<Product> findAll();

    void save(Product product);

    Product findById(String id);
}
