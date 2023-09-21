package com.example.forum.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.forum.bo.Comment;
import com.example.forum.bo.CsComment;
import com.example.forum.bo.User;
import com.example.forum.mongodbEntity.Tie;
import com.example.forum.mongodbEntity.TieComment;
import com.example.forum.mongodbEntity.TieCommentVo;
import com.example.forum.result.Result;
import com.example.forum.result.ResultCodeMessage;
import com.example.forum.service.ICommentService;
import com.example.forum.service.ICsCommentService;
import com.example.forum.service.ITieService;
import com.example.forum.service.IUserService;
import com.example.forum.vo.CommentVo;
import com.example.forum.mongodbEntity.TieVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public Result<com.example.forum.mongodbEntity.Tie> createTie(@RequestBody Tie tie, HttpSession session){
        int userId = (int) session.getAttribute("user_id");
        logger.info("createUserId:" + userId);

        String resultCode = tieService.createTie(tie.getTitle(), tie.getContent(), userId, tie.getCreateTime());
        tie.setCreateUserId(userId);
        if(resultCode == null){
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        return Result.success(tie);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = "application/json")
    public Result<com.example.forum.mongodbEntity.Tie> getTie(@RequestParam int id){
        com.example.forum.mongodbEntity.Tie tie = new com.example.forum.mongodbEntity.Tie();
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

    @RequestMapping(value = "/getCommentsByMongo", method = RequestMethod.POST)
    public Result<List<CommentVo>> getCommentsByMongo(@RequestBody HashMap<String, Object> jsonObject){

        List<CommentVo> commentVos = new ArrayList<>();
        try{
            logger.info("jsonObject:{}", jsonObject.toString());
            int tieId = Integer.parseInt(String.valueOf(jsonObject.get("tieId")));
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

    @RequestMapping(value = "/createCommentByMongo", method = RequestMethod.POST)
    public Result<TieCommentVo> createCommentByMongo(@RequestBody HashMap<String, Object> jsonObject, HttpSession session){
        int tieId = (int) jsonObject.get("tieId");
        int userId = (int) session.getAttribute("user_id");
        String content = String.valueOf(jsonObject.get("content"));
        TieCommentVo tieCommentVo = new TieCommentVo("", tieId, userId, content, null);
        try{
            TieComment tieComment = commentService.createCommentByMongo(userId, tieId, content);
            tieCommentVo.setCommentId(tieComment.getCommentId());
            tieCommentVo.setCreateTime(tieComment.getCreateTime());
        } catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        return Result.success(tieCommentVo);
    }

    @RequestMapping(value = "/pageCommentsByMongo", method = RequestMethod.GET)
    public Result<List<CommentVo>> pageCommentsByMongo(@RequestBody HashMap<String, Object> jsonObject){
        int pageSize = (int) jsonObject.get("pageSize");
        int pageNum = (int) jsonObject.get("pageNum");
        int tieId = (int) jsonObject.get("tieId");
        List<CommentVo> commentVos = new ArrayList<>();
        try{
            commentService.pageCommentListByMongo(tieId, pageNum, pageSize).forEach(tieCommentVo -> {
                User user = userService.getUser(tieCommentVo.getUserId());
                commentVos.add(new CommentVo(tieCommentVo.getCommentId(), tieCommentVo.getContent(), user.getId(), user.getName(), tieCommentVo.getCreateTime()));
            });
        } catch (Exception e){
            e.printStackTrace();
            logger.error("pageCommentsByMongo " + e.getMessage());
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        return Result.success(commentVos);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getTies", produces = "application/json")
    public Result<List<TieVo>> getTies(){
        List<TieVo> ties = new ArrayList<>();
        try{
            ties = tieService.getTies();
        }catch (Exception e){
            logger.error("TieController: getTies: " + e.getMessage());
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        return Result.success(ties);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getPageTie", produces = "application/json")
    public Result<List<TieVo>> getPageTie(@RequestParam int pageIndex, @RequestParam int pageSize){
        List<TieVo> tiePageInfo;
        try{
            tiePageInfo = tieService.getPageTie(pageIndex, pageSize);
        } catch (Exception e){
            logger.error("getPageTie " + e.getMessage());
            return Result.failure(ResultCodeMessage.SERVER_ERROR);
        }
        return Result.success(tiePageInfo);
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

    @PostMapping(value = "/deleteComment", produces = "application/json")
    public Result<Boolean>  deleteComment(@RequestParam String commentId) {
        return null;
    }





}
