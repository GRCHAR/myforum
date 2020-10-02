package com.example.forum.dao;

import com.example.forum.bo.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface VideoDao {

    @Insert("INSERT INTO video(id, title, userId, url) VALUES(#{id}, #{title}, #{userId}, #{url})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int createVideo(Video video);

    @Select("SELECT * FROM video WHERE id=#{id}")
    public Video getVideo(int id);


}
