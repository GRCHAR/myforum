package com.example.forum.service.impl;

import com.example.forum.bo.Tie;
import com.example.forum.controller.TieController;
import com.example.forum.dao.TieDao;
import com.example.forum.service.ITieService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    private final Logger logger = LoggerFactory.getLogger(TieServiceImpl.class);

    @Override
    public int createTie(String title, String content, int createUserId, Timestamp createTime) {
        try{
            tieDao.createTie(title, content, createTime, createUserId);
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

    @Override
    public PageInfo<Tie> getPageTie(int pageIndex, int pageSize){
        PageHelper.startPage(pageIndex, pageSize);
        List<Tie> ties = tieDao.getAllTie();
        return new PageInfo<Tie>(ties);
    }



}
