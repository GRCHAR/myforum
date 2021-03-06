package com.example.forum.controller;


import com.example.forum.bo.Comment;
import com.example.forum.bo.CsComment;
import com.example.forum.bo.Tie;
import com.example.forum.bo.User;
import com.example.forum.dao.TieDao;
import com.example.forum.result.Result;
import com.example.forum.result.ResultCodeMessage;
import com.example.forum.service.ICommentService;
import com.example.forum.service.ICsCommentService;
import com.example.forum.service.ITieService;
import com.example.forum.service.IUserService;
import com.example.forum.vo.CommentVo;
import org.elasticsearch.common.recycler.Recycler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping(value = "/create")
    public Result createTie(@RequestParam int createUserId,
                            @RequestParam Timestamp createTime,
                            @RequestParam String content){
        int resultCode = tieService.createTie(content, createUserId, createTime);
        if(resultCode == -1){
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        return Result.success();
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
            List<Comment> localComments = commentService.getCommentListTie(tieId, pageIndex, pageSize);
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
    public Result<List<Comment>> createComment(@RequestBody HashMap<String, String> map){
        List<Comment> comments = new ArrayList<>();
        int userId = Integer.parseInt(map.get("userId"));
        int tieId = Integer.parseInt(map.get("tieId"));
        String content = map.get("content");
        try{
            int result = commentService.createComment(userId, tieId, content);
            if(result == -1){
                return null;
            }
            comments = commentService.getCommentListTie(tieId, 0, 100);
        }catch (Exception e){
            logger.info("createComment:String content:" + content + " int userId:" + userId + " int tieId:" + tieId + " exception:" + e.getMessage());
            return null;
        }
        logger.info("createComment:String content:" + content + " int userId:" + userId + " int tieId:" + tieId);
        return Result.success(comments);
    }
}
