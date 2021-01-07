package com.example.forum.controller;

import com.example.forum.bo.Video;
import com.example.forum.result.Result;
import com.example.forum.result.ResultCodeMessage;
import com.example.forum.service.IVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author genghaoran
 */
@RestController
@RequestMapping(value = "/video")
public class VideoController {

    Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private IVideoService videoService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public int createVideo(@RequestBody HashMap<String, String> map){
        String title = map.get("title");
        int userId = Integer.parseInt(map.get("userId"));
        int videoId = 0;
        try{
            videoId = videoService.createService(new Video(title, userId));
        }catch (Exception e){
            logger.error("createVideo map" + map.toString());
            return 0;
        }

        return videoId;
    }

    public Result<Video> getVideoInfo(@RequestParam int videoId){
        Video video = new Video();
        try{
            video = videoService.getVideo(videoId);
        }catch (Exception e){
            logger.error("getVideoInfo videoId:" + videoId);
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
       return Result.success(ResultCodeMessage.SUCCESS, video);
    }



}
