package com.haojie.manage.dao;

import com.haojie.manage.domain.Traveller;

import java.util.List;

public interface ITravellerDao {

    List<Traveller> findByOrdersId(String id);
}
