package com.example.forum.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forum.bo.Tie;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author genghaoran
 */


public interface TieDao extends BaseMapper<Tie> {

    /**
     * 根据ID获取帖子
     * @param id 帖子ID
     * @return 返回Tie
     */
    @Select("SELECT * FROM tie WHERE id=#{id}")
    Tie getTieById(int id);

    /**
     * 将创建的帖子内容存入数据库
     * @param content 帖子内容
     * @param createTime 创建时间
     * @param createUserId 创建用户
     */
    @Insert("INSERT INTO tie VALUES(#{createTime}, #{createUserId}, #{content}, #{title})")
    void createTie(String title, String content, Timestamp createTime, int createUserId);

    /**
     * 获取所有帖子
     * @return 所有帖子的列表
     */
    @Select("SELECT * FROM tie")
    List<Tie> getAllTie();



}
