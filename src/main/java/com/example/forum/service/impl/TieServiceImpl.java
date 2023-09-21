package com.example.forum.service.impl;

import com.example.forum.mongodbDao.TieMongoDao;
import com.example.forum.mongodbEntity.Tie;
import com.example.forum.service.ITieService;
import com.example.forum.mongodbEntity.TieVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author genghaoran
 */
@Service
public class TieServiceImpl implements ITieService {

    @Autowired
    private TieMongoDao tieMongoDao;

    private final Logger logger = LoggerFactory.getLogger(TieServiceImpl.class);


    @Override
    public String createTie(String title, String content, int createUserId, Timestamp createTime) {
        Tie tie = new Tie(title, content,createUserId , new Timestamp(System.currentTimeMillis()));
        try{
            tieMongoDao.saveTie(tie);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
        return tie.getTieId();
    }

    @Override
    public Tie getTieById(int id) {
        Tie tie = new Tie();
        try{
            tie = tieMongoDao.findTieById(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return tie;
    }

    @Override
    public List<TieVo> getTies(){
        List<TieVo> ties = new ArrayList<>();
        try{
            logger.info("ties 1");

            ties = tieMongoDao.pageTie(1, 50);
            logger.info("ties:{}", ties);
        }catch (Exception e){
            logger.error("getTies " + e.getMessage());
            return null;
        }
        return ties;
    }

    @Override
    public List<TieVo> getPageTie(int pageIndex, int pageSize){
        return tieMongoDao.pageTie(pageIndex, pageSize);
    }

    @Override
    public void deleteTie(int tieId){
        tieMongoDao.removeTie(tieId);
    }



}
