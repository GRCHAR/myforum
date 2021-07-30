package com.example.forum.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.forum.bo.Comment;
import com.example.forum.bo.CsComment;
import com.example.forum.bo.Tie;
import com.example.forum.bo.User;
import com.example.forum.dao.TieDao;
import com.example.forum.mongodbEntity.TieComment;
import com.example.forum.mongodbEntity.TieCommentVo;
import com.example.forum.result.Result;
import com.example.forum.result.ResultCodeMessage;
import com.example.forum.service.ICommentService;
import com.example.forum.service.ICsCommentService;
import com.example.forum.service.ITieService;
import com.example.forum.service.IUserService;
import com.example.forum.vo.CommentVo;
import com.github.pagehelper.PageInfo;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.common.recycler.Recycler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author genghaoran
 */
@RestController
@RequestMapping(value = "/tie")
public class TieController {

    @Autowired
    private ITieService tieService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICsCommentService csCommentService;



    private Logger logger = LoggerFactory.getLogger(TieController.class);


    @PostMapping(value = "/create", produces = "application/json")
    public Result<Tie> createTie(@RequestBody Tie tie){
        logger.info("createUserId:" + tie.getCreateUserId());
        int resultCode = tieService.createTie(tie.getTitle(), tie.getContent(), tie.getCreateUserId(), tie.getCreateTime());
        if(resultCode == -1){
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        return Result.success(tie);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = "application/json")
    public Result<Tie> getTie(@RequestParam int id){
        Tie tie = new Tie();
        try{
            tie = tieService.getTieById(id);
        }catch(Exception e){
            logger.error("getTie error: param int id=" + id + e.getMessage());
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        logger.info("getTie param:int id=" + id + " return tie:" + tie.toString());
        return Result.success(tie);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getcomments", produces = "application/json")
    public Result<List<CommentVo>> getComments(@RequestParam int tieId,
                                               @RequestParam int pageIndex,
                                               @RequestParam int pageSize){
        List<CommentVo> comments = new ArrayList<>();
        try{
            List<Comment> localComments = commentService.getCommentListTie(tieId, pageIndex, pageSize).getRecords();
            for(Comment comment : localComments){
                logger.info("createTime:" + comment.getTimestamp());
                User user = userService.getUser(comment.getUserId());
                CommentVo commentVo = new CommentVo(comment.getCommentId(), comment.getContent(),
                        user.getId(), user.getName(), comment.getTimestamp());
                comments.add(commentVo);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        return Result.success(comments);
    }

    @RequestMapping(value = "/getCommentsByMongo", method = RequestMethod.GET)
    public Result<List<CommentVo>> getCommentsByMongo(@RequestBody HashMap<String, Object> jsonObject){

        List<CommentVo> commentVos = new ArrayList<>();
        try{
            logger.info("jsonObject:{}", jsonObject.toString());
            int tieId = (int) jsonObject.get("tieId");
            List<TieCommentVo> localComments = commentService.getCommentListByMongo(tieId);
            for(TieCommentVo comment : localComments){
                logger.info("createTime:" + comment.getCreateTime());
                User user = userService.getUser(comment.getUserId());
                CommentVo commentVo = new CommentVo(comment.getCommentId(), comment.getContent(),
                        user.getId(), user.getName(), comment.getCreateTime());
                commentVos.add(commentVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        return Result.success(commentVos);

    }


    @RequestMapping(method = RequestMethod.GET, value = "/getTies", produces = "application/json")
    public Result<List<Tie>> getTies(){
        List<Tie> ties = new ArrayList<>();
        try{
            ties = tieService.getTies();
        }catch (Exception e){
            logger.error("TieController: getTies: " + e.getMessage());
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        return Result.success(ties);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getPageTie", produces = "application/json")
    public Result<List<Tie>> getPageTie(@RequestParam int pageIndex, @RequestParam int pageSize){
        PageInfo<Tie> tiePageInfo;
        try{
            tiePageInfo = tieService.getPageTie(pageIndex, pageSize);
        } catch (Exception e){
            logger.error("getPageTie " + e.getMessage());
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        return Result.success(tiePageInfo.getList());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteTie", produces = "application/json")
    public Result<Object> deleteTie(@RequestParam int tieId){
        return Result.success();
    }




    @RequestMapping(method = RequestMethod.GET, value = "/getCsComments", produces = "application/json")
    public Result<List<CsComment>> getCsComment(@RequestParam int commentId){
        List<CsComment> csComments = new ArrayList<>();
        try{
            csComments = csCommentService.getCsComments(commentId);
        }catch (Exception e){
            logger.info("getCsComment: int commentId:" + commentId + " Exception:" + e.getMessage());
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        return Result.success(csComments);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createComment")
    public Result<IPage<Comment>> createComment(@RequestBody HashMap<String, String> map){
        IPage<Comment> comments;
        int userId = Integer.parseInt(map.get("userId"));
        int tieId = Integer.parseInt(map.get("tieId"));
        String content = map.get("content");
        try{
            int result = commentService.createComment(userId, tieId, content);
            if(result == -1){
                logger.error("createComment error");
                return Result.failure(ResultCodeMessage.SERVER_ERROR);
            }
            comments = commentService.getCommentListTie(tieId, 0, 100);
        }catch (Exception e){
            logger.info("createComment:String content:" + content + " int userId:" + userId + " int tieId:" + tieId + " exception:" + e.getMessage());
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        logger.info("createComment:String content:" + content + " int userId:" + userId + " int tieId:" + tieId);
        return Result.success(comments);
    }










}
