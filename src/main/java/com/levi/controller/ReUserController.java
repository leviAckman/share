package com.levi.controller;

import com.levi.bean.ReUser;
import com.levi.service.ReUserService;
import com.levi.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*登录 与 注册 控制类*/
@Controller
public class ReUserController {

    @Autowired
    @Qualifier("reUserServiceImpl")
    ReUserService reUserService;
    /*注册*/
    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public String register(ReUser ru, Model model){
       if(reUserService.register(ru)){
           return "login";
       }
        return "register";
    }

    /*登录*/
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String login(ReUser ru,  HttpServletResponse response){
        ReUser login = reUserService.login(ru);
        if(login != null){
            Cookie c1=new Cookie("reUser.u_name",login.getU_name());
            c1.setPath("/");
            Cookie c2=new Cookie("reUser.u_password",login.getU_password());
            c2.setPath("/");

            response.addCookie(c1);
            response.addCookie(c2);
        }
        return "redirect:/page/list";
    }

    /*查询余额*/
    @RequestMapping(value = "/user/balance")
    @ResponseBody
    public String balance(HttpServletRequest request){
        String u_name= CookieUtils.getCookieValue(request,"reUser.u_name");
        String u_password= CookieUtils.getCookieValue(request,"reUser.u_password");
        return String.valueOf(reUserService.resultBalance(u_name, u_password));
    }

    /*退出登录*/
    @RequestMapping("/user/signout")
    public String signOut(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        return "redirect:/page/list";
    }
}
