package com.work.auth.config;

import com.work.auth.service.impl.UserServerImpl;
import com.work.auth.util.Md5Util;
import com.work.auth.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author ZhaiYunpeng
 */
@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserServerImpl userServer;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 这个获取表单输入中返回的用户名;
        String userName = (String) authentication.getName();
        // 这个是表单中输入的密码；
        String password = (String) authentication.getCredentials();

        String encodePwd = Md5Util.md5Encrypt32Upper(password);

        UserDetails userInfo = userServer.loadUserByUsername(userName);

        if (userInfo == null || !StringUtil.equals(userInfo.getPassword(),encodePwd)) {
            throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
        }

        return new UsernamePasswordAuthenticationToken(userName, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
