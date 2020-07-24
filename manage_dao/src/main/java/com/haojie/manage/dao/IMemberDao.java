package com.haojie.manage.dao;

import com.haojie.manage.domain.Member;

public interface IMemberDao {

    Member findById(String id);
}
