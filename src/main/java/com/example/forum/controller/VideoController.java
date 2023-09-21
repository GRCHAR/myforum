package com.example.forum.controller;

import com.example.forum.bo.Video;
import com.example.forum.result.Result;
import com.example.forum.result.ResultCodeMessage;
import com.example.forum.service.IVideoService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author genghaoran
 */
@RestController
@RequestMapping(value = "/video")
public class VideoController {

    Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private IVideoService videoService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

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


    @Transactional
    @RequestMapping(value = "/info", method = RequestMethod.GET)
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

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Result<List<Video>> selectPageVideo(@RequestParam int pageIndex,
                                               @RequestParam int pageSize,
                                               @RequestParam int sortType){
        PageInfo<Video> videoPageInfo;
        try{
        videoPageInfo = videoService.selectPageVideo(pageIndex, pageSize);
        }catch (Exception e){
            logger.error("selectPageVideo" + e.getMessage());
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        return Result.success(videoPageInfo.getList());
    }

    @GetMapping(value = "/kafkaSend")
    public Result<String> testKafkaSend(@RequestParam String message) {

        kafkaTemplate.send("register", message);
        logger.info("发送kafka消息成功");
        return new Result<>("success");
    }



}
