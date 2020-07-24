package com.haojie.manage.service;

import com.haojie.manage.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    void save(SysLog sysLog);

    List<SysLog> findAll();
}
