package com.example.forum.mongodbDao;

import com.example.forum.mongodbEntity.TieComment;

/**
 * @author genghaoran
 */
public interface TieCommentDao {

    /**
     * 创建帖子评论
     * @param tieComment 帖子评论对象
     */
    void saveTieComment(TieComment tieComment);

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
     * @param id 帖子ID
     * @return 返回查找到的帖子对象
     */
    TieComment findCommentById(int id);
}
