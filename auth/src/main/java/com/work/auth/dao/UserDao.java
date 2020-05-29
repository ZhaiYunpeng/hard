package com.work.auth.dao;

import com.work.auth.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    User selectByUserName(@Param("username") String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}