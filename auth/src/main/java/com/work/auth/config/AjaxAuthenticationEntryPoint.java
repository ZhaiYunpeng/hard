package com.work.auth.config;

import com.alibaba.fastjson.JSON;
import com.work.auth.base.ResultDto;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户没有登录时返回给前端的数据
 * @author ZhaiYunpeng
 */
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultDto dto = new ResultDto();
        dto.setCode(-2);
        dto.setMsg("Need Authorities!");
        httpServletResponse.getWriter().write(JSON.toJSONString(dto));
    }
}
