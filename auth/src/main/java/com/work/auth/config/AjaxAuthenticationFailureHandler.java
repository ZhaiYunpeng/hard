package com.work.auth.config;

import com.alibaba.fastjson.JSON;
import com.work.auth.base.ResultDto;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录失败时返回给前端的数据
 * @author ZhaiYunpeng
 */
@Component
public class AjaxAuthenticationFailureHandler  implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultDto dto = new ResultDto();
        dto.setCode(400);
        dto.setMsg("Login Failure!");
        httpServletResponse.getWriter().write(JSON.toJSONString(dto));
    }
}
