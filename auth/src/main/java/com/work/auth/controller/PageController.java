package com.work.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ZhaiYunpeng
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/systemPage/{pageName}")
    public String getPage(@PathVariable String pageName) {
        return "systemPage/" + pageName;
    }

    @RequestMapping("/view/{pageName}")
    public String getView(@PathVariable String pageName) {
        return "view/" + pageName;
    }
}
