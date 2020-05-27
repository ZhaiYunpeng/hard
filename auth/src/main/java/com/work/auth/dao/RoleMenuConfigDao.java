package com.work.auth.dao;

import com.work.auth.pojo.RoleMenuConfig;

public interface RoleMenuConfigDao {
    int deleteByPrimaryKey(String id);

    int insert(RoleMenuConfig record);

    int insertSelective(RoleMenuConfig record);

    RoleMenuConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleMenuConfig record);

    int updateByPrimaryKey(RoleMenuConfig record);
}