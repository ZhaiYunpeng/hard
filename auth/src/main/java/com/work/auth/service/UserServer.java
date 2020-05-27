package com.work.auth.service;

import com.work.auth.pojo.User;

import java.util.List;

/**
 * @author ZhaiYunpeng
 */
public interface UserServer {
    /**
     * 新增用户
     *
     * @param user 用户实体类
     * @return int
     */
    int insertUser(User user);

    /**
     * 根据ID删除用户
     *
     * @param id ID
     * @return int
     */
    int delUserById(String id);

    /**
     * 根据ID查询用户
     *
     * @param id ID
     * @return User
     */
    User getUserById(String id);

    /**
     * 根据参数查询用户
     *
     * @param user Params
     * @return List
     */
    List<User> selectUserByParams(User user);

    /**
     * 更新用户
     *
     * @param user Entity
     * @return int
     */
    int updateUser(User user);


}
