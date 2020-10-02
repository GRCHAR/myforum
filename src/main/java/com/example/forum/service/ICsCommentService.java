package com.example.forum.service;

import com.example.forum.bo.CsComment;

import java.util.List;

/**
 * @author genghaoran
 */
public interface ICsCommentService {

    /**
     * 获取次级评论
     * @param commentId 评论ID
     * @return 次级评论列表
     */
    public List<CsComment> getCsComments(int commentId);
}
