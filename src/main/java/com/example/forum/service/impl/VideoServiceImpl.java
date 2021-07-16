package com.example.forum.service.impl;

import com.example.forum.bo.Video;
import com.example.forum.dao.VideoDao;
import com.example.forum.service.IVideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author genghaoran
 */
@Service
public class VideoServiceImpl implements IVideoService {

    @Autowired
    private VideoDao videoDao;

    Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Override
    public int createService(Video video) {
        logger.info("createVideo: Video:" + video.toString());
        return videoDao.createVideo(video);
    }


    @Override
    public Video getVideo(int id) {
        logger.info("getVideo: int id:" + id);
        return videoDao.getVideo(id);
    }

    @Override
    public PageInfo<Video>  selectPageVideo(int pageIndex, int pageSize){
        PageHelper.startPage(pageIndex, pageSize);
        List<Video> videos = videoDao.getVideoPages();
        return new PageInfo<Video>(videos);
    }

    



}
