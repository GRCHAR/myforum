package com.example.forum.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author genghaoran
 */

public class LoginInterceptor implements HandlerInterceptor {

    String LOGIN_ID;

    String NULL = "null";

    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        LOGIN_ID = String.valueOf(session.getAttribute("user_id"));
        logger.info(LOGIN_ID);
        if(Objects.equals(LOGIN_ID, NULL)){
            logger.info("未登录接口请求");
            response.sendError(500, "未登录");
        }
        return !Objects.equals(LOGIN_ID, NULL);
    }


}
