package com.work.auth.config;

import com.alibaba.fastjson.JSON;
import com.work.auth.base.ResultDto;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 无权访问
 * @author ZhaiYunpeng
 */
@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResultDto dto = new ResultDto();
        dto.setCode(403);
        dto.setMsg("Need Authorities!");
        httpServletResponse.getWriter().write(JSON.toJSONString(dto));
    }
}
