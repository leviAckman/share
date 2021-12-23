package com.levi.controller;

import com.alibaba.fastjson.JSON;
import com.levi.bean.ReData;
import com.levi.service.ReDataService;
import com.levi.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Controller
public class ReDataController {
    @Autowired
    @Qualifier("reDataServiceImpl")
    ReDataService reDataService;

    /*获取未购买的reData*/
    @RequestMapping("data/ajax/{pageNum}")
    @ResponseBody
    public String getAjax(HttpServletRequest request,@PathVariable("pageNum")int pn){
        int pageNum=pn-1;
        if(pageNum < 0){
            pageNum=0;
        }
        if(pn > 0){
            pageNum = pageNum*3;
        }
        /*拿到用户信息*/
        String name= CookieUtils.getCookieValue(request,"reUser.u_name");
        String password= CookieUtils.getCookieValue(request,"reUser.u_password");
        /*查询*/
        if (name != null && password != null) {
            return JSON.toJSONString(reDataService.findBuy(name, password,pageNum, "false"));
        }
        return JSON.toJSONString(reDataService.finAll(pageNum));
    }

    /*获取已购买的reData*/
    @RequestMapping("data/ajaxbuy/{pageNum}")
    @ResponseBody
    public String getAjaxBuy(HttpServletRequest request,@PathVariable("pageNum")int pn){
        int pageNum=pn-1;
        if(pageNum < 0){
            pageNum=0;
        }
        if(pn > 0){
            pageNum = pageNum*2;
        }
        /*拿到用户信息*/
        String name= CookieUtils.getCookieValue(request,"reUser.u_name");
        String password= CookieUtils.getCookieValue(request,"reUser.u_password");
        /*查询*/
        if (name != null && password != null) {
            return JSON.toJSONString(reDataService.findBuy(name,password,pageNum, "true"));
        }
        return null;
    }

    /*购买*/
    @RequestMapping("data/buy/{id}")
    public String buy(@PathVariable("id") int d_id,HttpServletRequest request){
        String u_name= CookieUtils.getCookieValue(request,"reUser.u_name");
        String u_password= CookieUtils.getCookieValue(request,"reUser.u_password");
        if (u_name != null && u_password != null && d_id != 0) {
            /*购买*/
            reDataService.buy(u_name,u_password,d_id);
        }
        return "redirect:/page/list";
    }

    /*上传文件*/
    @RequestMapping("data/upload")
    public String upload(@RequestParam("d_name")String name,@RequestParam("d_price")double price
            ,@RequestParam("poster") CommonsMultipartFile poster
            ,@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        String realPath = request.getServletContext().getRealPath("/data");
        String posterName=realPath+"/poster/"+poster.getOriginalFilename();
        String fileName=realPath+"/target/"+file.getOriginalFilename();
        if(posterName.equals("") || posterName == null || fileName.equals("") || fileName == null || price == 0.0){
            return "redirect:/page/upload";
        }
        /*保存 poster*/
        poster.transferTo(new File(posterName));
        /*保存 file*/
        file.transferTo(new File(fileName));

        /*持久化数据*/
        ReData reData = new ReData();
        reData.setD_name(name);
        reData.setD_price(price);
        reData.setD_poster("data/poster/"+poster.getOriginalFilename());
        reData.setD_download("data/target/"+file.getOriginalFilename());
        if(!reDataService.insertReData(reData)){
            return "redirect:/page/upload";
        }
        return "redirect:/page/list";
    }

    /*获取页码总数*/
    @RequestMapping("data/count")
    @ResponseBody
    public String pageCount1(HttpServletRequest request){
        /*拿到用户信息*/
        String name= CookieUtils.getCookieValue(request,"reUser.u_name");
        String password= CookieUtils.getCookieValue(request,"reUser.u_password");
        /*查询*/
        if (name != null && password != null) {
            return reDataService.pageCount(name, password, false);
        }else{
            return reDataService.count();
        }
    }

    /*获取页码总数*/
    @ResponseBody
    @RequestMapping("data/countbuy")
    public String pageCount2(HttpServletRequest request){
        /*拿到用户信息*/
        String name= CookieUtils.getCookieValue(request,"reUser.u_name");
        String password= CookieUtils.getCookieValue(request,"reUser.u_password");
        /*查询*/
        if (name != null && password != null) {
            return reDataService.pageCount(name, password, true);
        }
        return null;
    }
}
