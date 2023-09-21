package com.example.forum.mongodbDao;

import com.example.forum.mongodbEntity.Tie;
import com.example.forum.mongodbEntity.TieVo;

import java.util.List;

/**
 * @author genghaoran
 */
public interface TieMongoDao {

    /**
     * 创建帖子
     * @param tie 帖子对象
     */
    void saveTie(Tie tie);

    /**
     * 根据ID删除帖子
     * @param id 帖子ID
     */
    void removeTie(int id);

    /**
     * 更新帖子
     * @param tie 帖子对象
     */
    void updateTie(Tie tie);



    /**
     * 根据ID查找帖子
     * @param id 帖子ID
     * @return 返回查找到的帖子对象
     */
    Tie findTieById(int id);

    List<TieVo> pageTie(int pageNum, int pageSize);
}
