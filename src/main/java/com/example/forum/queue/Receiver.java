package com.example.forum.queue;

import com.example.forum.bo.Video;
import com.example.forum.dao.VideoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.nio.channels.Channel;

/** @author genghaoran */
@Component
@RabbitListener(queues = "upload.video")
public class Receiver {

  private final Logger logger = LoggerFactory.getLogger(Receiver.class);

  private final String CODE = "code";

  private final int UPLOADING = 2;

  private final int SUCCESS = 0;

  private final int FAILE = 1;

  @Autowired private VideoDao videoDao;

  @RabbitHandler
  public void queueReceiver(String jsonData) {

    try {

      JSONObject uploadVideo = new JSONObject(jsonData);
      if (uploadVideo.getInt(CODE) == UPLOADING) {
        Video video = new Video();
        video.setId(uploadVideo.getInt("videoId"));
        video.setTitle(uploadVideo.getString("title"));
        video.setUserId(uploadVideo.getInt("userId"));
        video.setState("upload");
        if (videoDao.selectById(video.getId()) == null) {
          videoDao.insert(video);
        }
      } else if (uploadVideo.getInt(CODE) == FAILE || uploadVideo.getInt(CODE) == SUCCESS) {
        String title = uploadVideo.getString("title");
        int userId = uploadVideo.getInt("userId");
        String state = uploadVideo.getString("state");
        int id = uploadVideo.getInt("videoId");
        logger.info(
            "Receive video id:{}, title:{}, userId:{}, state:{}",
            uploadVideo.getInt("videoId"),
            title,
            userId,
            state);
        Video video = new Video(id, title, userId, state);
        videoDao.updateById(video);
      } else {
        logger.error("queue receiver error: {}", jsonData);
      }
    } catch (JSONException e) {
      logger.error("queue receiver error: {}", jsonData);
      e.printStackTrace();
    }
  }
}
