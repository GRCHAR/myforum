package com.example.forum.service.impl;

import com.example.forum.bo.Tie;
import com.example.forum.controller.TieController;
import com.example.forum.dao.TieDao;
import com.example.forum.service.ITieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
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
    private TieDao tieDao;

    private Logger logger = LoggerFactory.getLogger(TieServiceImpl.class);

    @Override
    public int createTie(String content, int createUserId, Timestamp createTime) {
        try{
            tieDao.createTie(content, createTime, createUserId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Tie getTieById(int id) {
        Tie tie = new Tie();
        try{
            tie = tieDao.getTieById(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return tie;
    }

    @Override
    public List<Tie> getTies(){
        List<Tie> ties = new ArrayList<>();
        try{
            ties = tieDao.getAllTie();
        }catch (Exception e){
            logger.error("getTies " + e.getMessage());
            return null;
        }
        return ties;
    }


}
