package com.levi.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {

    /*获取cookie的值*/
    public static String getCookieValue(HttpServletRequest request, String cookieKey){
        if(request.getCookies() != null){
            for (Cookie cookie : request.getCookies()) {
                if(cookie.getName().equals(cookieKey)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
