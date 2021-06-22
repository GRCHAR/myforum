package com.example.forum.controller;

import com.example.forum.bo.Comment;
import com.example.forum.bo.User;
import com.example.forum.dao.CommentDao;
import com.example.forum.dao.UserDao;
import com.example.forum.result.Result;
import com.example.forum.result.ResultCodeMessage;
import com.example.forum.service.IUserService;
import jdk.nashorn.internal.runtime.ECMAException;
import org.apache.http.HttpResponse;
import org.hibernate.boot.spi.InFlightMetadataCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.forum.result.Result.failure;
import static com.example.forum.result.Result.success;

/**
 * @author genghaoran
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IUserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<Integer> registerUser(@RequestBody HashMap<String, String> map,
                                        HttpServletResponse httpServletResponse,
                                        HttpSession session){
        int userId = 0;
        String name = map.get("name");
        String password = map.get("password");
        try{
            userId = userService.register(name, password);
        }catch (Exception e){
            logger.error("registerUser error, String name:" + name + " password:" + password + " exception:" + e.getMessage());
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        logger.info("registerUser, String name:" + name + " password:" + password);
        session.setAttribute("userId", userId);
        Cookie cookie = new Cookie("userId", String.valueOf(userId));
        cookie.setMaxAge(30 * 60);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        Integer integer = userId;
        return Result.success(integer);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<Integer> loginUser(@RequestBody HashMap<String, String> map,
                                     HttpServletResponse httpServletResponse,
                                     HttpSession session){
        int userId = 0;
        String name = map.get("name");
        String password = map.get("password");
        try{
            userId = userService.login(name, password);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("login, String name:" + name + " password:" + password + " " +  e.getMessage());
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        session.setAttribute("userId", userId);
        Cookie cookie = new Cookie("userId", String.valueOf(userId));
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        return Result.success(userId);
    }

    @RequestMapping(value = "/getComments", method = RequestMethod.GET, produces = "application/json")
    public Result<List<Comment>> getUserComments(@RequestParam int userId){
        List<Comment> comments = new ArrayList<>();
        try{
            comments = commentDao.getCommentByUserId(userId);
        }catch(Exception e){
            e.printStackTrace();
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        return success(comments);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = "application/json")
    public Result<User> getUser(@RequestParam int userId){
        User user = new User();
        try{
           user = userService.getUser(userId);
        }catch (Exception e){
            logger.info("UserController: getUser: int userId " + e.getMessage());
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        return success(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json")
    public Result<User> logoutUser(HttpServletResponse response, HttpSession session){
        User user = new User();
        try{
            session.removeAttribute("userId");
            session.invalidate();
        } catch (Exception e){
            return failure(ResultCodeMessage.SERVER_ERROR);
        }
        return success(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    public Result<User> setUser(@RequestParam String name,
                                @RequestParam String password,
                                HttpSession session){
        User user = new User();
        try{
            int userId = (int) session.getAttribute("userId");
            userDao.updateUser(userId, name, password);
            user = userDao.getUser(userId);
        } catch (Exception e){
            return failure(ResultCodeMessage.SERVER_ERROR);
        }
        return success(user);
    }



}
