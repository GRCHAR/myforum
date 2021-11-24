package com.example.forum.mongodbDao;

import com.example.forum.mongodbEntity.TieComment;
import com.example.forum.mongodbEntity.TieCommentVo;

import java.util.List;

/**
 * @author genghaoran
 */
public interface TieCommentDao {

    /**
     * 创建帖子评论
     * @param tieComment 帖子评论对象
     * @return 创建结果对象
     *
     */
    TieComment saveTieComment(TieComment tieComment);

    /**
     * 根据ID删除帖子评论
     * @param id 帖子评论ID
     */
    void removeTieComment(int id);

    /**
     * 更新帖子评论
     * @param tieComment 帖子评论对象
     */
    void updateComment(TieComment tieComment);

    /**
     * 根据ID查找帖子
     * @param tieId 帖子ID
     * @return 返回查找到的帖子对象
     */
    List<TieCommentVo> findCommentById(int tieId);

    List<TieCommentVo> pageComment(int tieId, int pageNum, int pageSize);
}
