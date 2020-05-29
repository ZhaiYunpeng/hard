package com.work.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ZhaiYunpeng
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
    public String indexPage(){
        return "index";
    }
}
