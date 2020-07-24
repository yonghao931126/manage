package com.haojie.manage.dao;

import com.haojie.manage.domain.SysLog;

import java.util.List;

public interface ISysLogDao {

    void save(SysLog sysLog);

    List<SysLog> findAll();
}
