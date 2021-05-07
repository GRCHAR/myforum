package com.example.forum.service;


import com.example.forum.bo.Video;
import com.github.pagehelper.PageInfo;

import java.util.List;

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

    /**
     * 获取视频分页信息
     * @param pageIndex 页码
     * @param pageSize 页尺寸
     * @return 当前页数据
     */
    public PageInfo<Video> selectPageVideo(int pageIndex, int pageSize);
}
