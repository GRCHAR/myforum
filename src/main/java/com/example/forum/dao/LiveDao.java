package com.example.forum.dao;

import com.example.forum.bo.Live;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface LiveDao {

    /**
     * 根据直播ID获取LIVE对象信息
     * @param liveId 直播ID
     * @return 直播信息
     */
    @Select("SELECT * FROM Live WHERE id=#{liveId}")
    Live getLive(int liveId);


    /**
     * 获取所有的直播对象信息
     * @return 所有直播信息
     */
    @Select("SELECT * FROM Live")
    List<Live> getLives();
}
