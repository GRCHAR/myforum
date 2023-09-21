package com.example.forum.service;

import com.example.forum.mongodbEntity.Tie;
import com.example.forum.mongodbEntity.TieVo;

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
    public String createTie(String tilte, String content, int createUserId, Timestamp createTime);

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
//    public List<Tie> getTies();

    List<TieVo> getTies();

    /**
     * 获取贴子分页数据
     * @param pageIndex 页码
     * @param pageSize 分页尺寸
     * @return 当前页数据
     */
    public List<TieVo> getPageTie(int pageIndex, int pageSize);


    /**
     * 删除帖子
     * @param tieId 帖子ID
     * @return 返回被删除的帖子ID
     */
    void deleteTie(int tieId);


}
