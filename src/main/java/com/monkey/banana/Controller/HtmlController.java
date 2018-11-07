package com.monkey.banana.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class HtmlController {
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String registerHTMLDeal() {
        System.out.println("[网页控制器]注册页面启动");
        return "/register";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String indexHTMLDeal() {
        System.out.println("[网页控制器]主监控页面启动");
        return "/index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginHTMLDeal() {
        System.out.println("[网页控制器]登录页面启动");
        return "/login";
    }
}
