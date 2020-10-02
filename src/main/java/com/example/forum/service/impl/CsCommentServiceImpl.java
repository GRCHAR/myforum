package com.example.forum.service.impl;

import com.example.forum.bo.CsComment;
import com.example.forum.dao.CsCommentDao;
import com.example.forum.service.ICsCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author genghaoran
 */
@Service
public class CsCommentServiceImpl  implements ICsCommentService {

    @Autowired
    private CsCommentDao csCommentDao;

    Logger logger = LoggerFactory.getLogger(CsCommentServiceImpl.class);

    @Override
    public List<CsComment> getCsComments(int commentId) {
        List<CsComment> csComments = new ArrayList<>();
        try{
            csComments = csCommentDao.findAllCsComment(commentId);
        }catch (Exception e){
            logger.info("getCsComments: int commentId:" + commentId + " " + e.getMessage());
            return null;
        }
        return csComments;
    }
}
