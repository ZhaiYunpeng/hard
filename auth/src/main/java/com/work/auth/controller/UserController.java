package com.work.auth.controller;

import com.github.pagehelper.PageInfo;
import com.work.auth.base.ResultDto;
import com.work.auth.pojo.User;
import com.work.auth.service.UserServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhaiYunpeng
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserServer userServer;

    @PostMapping("/getUserInfoById")
    public ResultDto<User> getUserInfoById(String id) {
        User userById = userServer.getUserById(id);
        PageInfo pageInfo = new PageInfo();
        return new ResultDto<>(userById);
    }

    @PostMapping("/createUser")
    public ResultDto<User> createUser(User user) {
        int insertUser = userServer.insertUser(user);
        return new ResultDto<>(insertUser == 1 ? 0 : -1);
    }
}
