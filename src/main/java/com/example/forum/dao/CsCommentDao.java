package com.example.forum.dao;

import com.example.forum.bo.CsComment;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author genghaoran
 */
public interface CsCommentDao {


    /**
     *获取对于评论的所有评论
     * @param commentId 评论ID
     * @return 次级评论列表
     */
    @Select("SELECT * FROM cscomment WHERE commentId=#{commentId}")
    public List<CsComment> findAllCsComment(int commentId);


}
