package com.example.forum.service;


import com.example.forum.bo.Video;

/**
 * @author genghaoran
 */
public interface IVideoService {

    /**
     * 添加视频信息
     * @param video 视频对象
     * @return 新增视频对象ID
     */
    public int createService(Video video);


    /**
     * 根据视频ID获取视频对象
     * @param id 视频ID
     * @return 视频对象
     */
    public Video getVideo(int id);
}
