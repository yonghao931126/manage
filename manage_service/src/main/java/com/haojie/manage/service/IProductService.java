package com.haojie.manage.service;

import com.haojie.manage.domain.Product;

import java.util.List;

public interface IProductService {

    /**
     * 查询所有商品
     * @return
     */
    List<Product> findAll();

    /**
     * 新增商品
     * @param product
     */
    void save(Product product);
}
