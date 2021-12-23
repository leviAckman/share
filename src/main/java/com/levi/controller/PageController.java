package com.levi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/*控制访问页面*/
@Controller
public class PageController {
    @RequestMapping("page/login")
    public String pageLogin(){
        return "login";
    }

    @RequestMapping("page/register")
    public String pageRegister(){
        return "register";
    }

    @RequestMapping("page/list")
    public String pageListLogin(HttpServletRequest re){
        return "list";
    }

    @RequestMapping("page/upload")
    public String pageUpload(HttpServletRequest re){
        return "upload";
    }
}
