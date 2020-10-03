package com.example.forum.controller;


import com.example.forum.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author genghaoran
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {


    @Autowired
    private IUserService userService;



}
