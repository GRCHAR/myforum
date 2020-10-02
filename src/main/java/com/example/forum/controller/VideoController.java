package com.example.forum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/video")
public class VideoController {

    Logger logger = LoggerFactory.getLogger(VideoController.class);


}
