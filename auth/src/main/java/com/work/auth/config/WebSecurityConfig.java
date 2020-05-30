package com.work.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 开启 SpringSecurity注解
 *
 * @author ZhaiYunpeng
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 未登陆时返回 JSON 格式的数据给前端（否则为 html）
     */
    @Autowired
    AjaxAuthenticationEntryPoint authenticationEntryPoint;

//    @Autowired
//    AjaxAuthenticationSuccessHandler authenticationSuccessHandler;  // 登录成功返回的 JSON 格式数据给前端（否则为 html）
    /**
     * 登录失败返回的 JSON 格式数据给前端（否则为 html）
     */
    @Autowired
    AjaxAuthenticationFailureHandler authenticationFailureHandler;

//    @Autowired
//    AjaxLogoutSuccessHandler  logoutSuccessHandler;  // 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）
    /**
     * 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）
     */
    @Autowired
    AjaxAccessDeniedHandler accessDeniedHandler;
    /**
     * 自定义安全认证
     */
    @Autowired
    SelfAuthenticationProvider provider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加入自定义的安全认证
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] str = new String[]{"/swagger-ui.html", "/swagger-resources/**", "/images/**", "/webjars/**"
                , "/v2/api-docs", "/configuration/ui", "/configuration/security"};

        http.csrf().disable()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()

                .antMatchers("/global/**", "/static/**").permitAll()
//                .antMatchers(str).permitAll()

                .anyRequest()
                .authenticated()// 其他 url 需要身份认证

                .and()
                //开启登录
                .formLogin()
                // 登录页面
                .loginPage("/login")
                .successForwardUrl("/")
//                .successHandler(authenticationSuccessHandler) // 登录成功
                //登录失败
                .failureHandler(authenticationFailureHandler)
                .permitAll()

                .and()
                .logout()
//                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();
        http.headers().frameOptions().sameOrigin();
        // 无权访问 JSON 格式的数据
//        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    }
}
