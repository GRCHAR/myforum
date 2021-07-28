package com.example.forum.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author genghaoran
 */

public class LoginInterceptor implements HandlerInterceptor {

    String LOGIN_ID;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        LOGIN_ID = (String) session.getAttribute("login_id");
        if( LOGIN_ID != null){
            response.sendRedirect("/login");
        }
        return session.getAttribute("login_id") != null;
    }


}
