package com.example.forum.service;

import com.example.forum.bo.Tie;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author genghaoran
 */
public interface ITieService {
    /**
     * 创建帖子
     * @param content 帖子内容
     * @param createUserId 创建用户id
     * @param createTime 创建时间
     * @return 返回是否成功
     */
    public int createTie(String content, int createUserId, Timestamp createTime);

    /**
     * 根据ID获取帖子
     * @param id 帖子id
     * @return 返回Tie对象
     */
    public Tie getTieById(int id);

    /**
     * 获取全部帖子
     * @return 全部帖子列表
     */
    public List<Tie> getTies();
}
