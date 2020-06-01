package com.work.auth.controller.rest;

import com.github.pagehelper.PageInfo;
import com.work.auth.base.BaseController;
import com.work.auth.base.ResultDto;
import com.work.auth.pojo.User;
import com.work.auth.service.UserServer;
import com.work.auth.util.StringUtil;
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
public class UserController extends BaseController {
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
    public ResultDto createUser(User user) {
        int code = 0;
        if(StringUtil.isNotEmpty(user.getUserAlias()) && StringUtil.isNotEmpty(user.getUserStatus())){
            code = userServer.insertUser(user);
        }
        return new ResultDto(code == 1 ? 0 : -1);
    }
    @PostMapping("/selectUserByParams")
    @ApiOperation(value = "根据参数查询用户")
    public PageInfo<User> selectUserByParams(User user,int pageNum,int pageSize){
        String currentUserId = super.getRequestIp();
        System.out.println(currentUserId);
        return userServer.selectUserByParams(user, pageNum, pageSize);
    }
}
