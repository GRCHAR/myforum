package com.example.forum.interceptor;

import com.example.forum.service.cache.IUserCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author genghaoran
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    String LOGIN_ID;

    String NULL = "null";

    @Autowired
    private IUserCacheService userCacheService;

    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        LOGIN_ID = String.valueOf(session.getAttribute("user_id"));
        logger.info(LOGIN_ID);
        if(Objects.equals(LOGIN_ID, NULL)){
            logger.info("未登录接口请求");
            response.sendError(500, "未登录");
        } else {
            if(!userCacheService.getUserById(Integer.parseInt(LOGIN_ID))){
                return false;
            }
        }
        return !Objects.equals(LOGIN_ID, NULL);
    }


}
