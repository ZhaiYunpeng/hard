package com.work.auth.controller;

import com.work.auth.base.ResultDto;
import com.work.auth.pojo.User;
import com.work.auth.service.UserServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * @author ZhaiYunpeng
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户API")
public class UserController {
    @Resource
    private UserServer userServer;

    @PostMapping("/getUserInfo")
    @ApiOperation("获取当前登录用户信息")
    public Principal getUserInfo(Principal principal){
        return principal;
    }

    @PostMapping("/getUserInfoById")
    @ApiOperation(value = "根据用户ID查询用户信息")
    public ResultDto<User> getUserInfoById(String id) {
        User userById = userServer.getUserById(id);
        return new ResultDto<>(userById);
    }

    @PostMapping("/createUser")
    @ApiOperation(value = "新建用户")
    public ResultDto<User> createUser(User user) {
        int insertUser = userServer.insertUser(user);
        return new ResultDto<>(insertUser == 1 ? 0 : -1);
    }
}
