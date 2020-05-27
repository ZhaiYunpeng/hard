package com.work.auth.dao;

import com.work.auth.pojo.UserRoleConfig;

public interface UserRoleConfigDao {
    int deleteByPrimaryKey(String id);

    int insert(UserRoleConfig record);

    int insertSelective(UserRoleConfig record);

    UserRoleConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRoleConfig record);

    int updateByPrimaryKey(UserRoleConfig record);
}