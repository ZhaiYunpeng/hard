package com.work.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.work.auth.dao.UserDao;
import com.work.auth.pojo.User;
import com.work.auth.service.UserServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhaiYunpeng
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServerImpl implements UserDetailsService, UserServer {
    /**
     * 用户Dao
     */
    @Resource
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.selectByUserName(username);
        return user;
    }


    /**
     * 新增用户
     *
     * @param user 用户实体类
     * @return int
     */
    @Override
    public int insertUser(User user) {
        user.setId(User.nextId());
        return userDao.insertSelective(user);
    }

    /**
     * 根据ID删除用户
     *
     * @param id ID
     * @return int
     */
    @Override
    public int delUserById(String id) {
        return userDao.deleteByPrimaryKey(id);
    }

    /**
     * 更新用户
     *
     * @param user Entity
     * @return int
     */
    @Override
    public int updateUser(User user) {
        return userDao.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据ID查询用户
     *
     * @param id ID
     * @return User
     */
    @Override
    public User getUserById(String id) {
        return userDao.selectByPrimaryKey(id);
    }

    /**
     * 根据参数查询用户
     *
     * @param user     查询参数
     * @param pageNum  页数
     * @param pageSize 每页条数
     * @return List
     */
    @Override
    public PageInfo<User> selectUserByParams(User user, int pageNum, int pageSize) {
        if (pageNum == 0) {
            pageNum = 1;
        }
        if (pageSize == 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userDao.selectByParamsAndPage(user);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }


}
