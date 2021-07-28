package com.example.forum.mongodbDao;

import com.example.forum.mongodbEntity.TieComment;

/**
 * @author genghaoran
 */
public interface TieMongoDao {

    /**
     * 创建帖子
     * @param tieComment 帖子对象
     */
    void saveTieComment(TieComment tieComment);

    /**
     * 根据ID删除帖子
     * @param id 帖子ID
     */
    void removeTieComment(int id);

    /**
     * 更新帖子
     * @param tieComment 帖子对象
     */
    void updateComment(TieComment tieComment);

    /**
     * 根据ID查找帖子
     * @param id 帖子ID
     * @return 返回查找到的帖子对象
     */
    TieComment findCommentById(int id);
}
