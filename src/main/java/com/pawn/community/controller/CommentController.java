package com.pawn.community.controller;

import com.pawn.community.dto.ArticleDetailsDTO;
import com.pawn.community.dto.CommentCreateDTO;
import com.pawn.community.dto.ResultDTO;
import com.pawn.community.enums.CommentTypeEnum;
import com.pawn.community.enums.NotificationTypeEnum;
import com.pawn.community.exception.CustomizeErrorCode;
import com.pawn.community.exception.CustomizeException;
import com.pawn.community.mapper.ArticleDetailsDTOMapper;
import com.pawn.community.mapper.CommentMapper;
import com.pawn.community.mapper.DetailsMapper;
import com.pawn.community.mapper.SecommentMapper;
import com.pawn.community.pojo.Comment;
import com.pawn.community.pojo.Notification;
import com.pawn.community.pojo.Secomment;
import com.pawn.community.pojo.User;
import com.pawn.community.service.CommentServiceImpl;
import com.pawn.community.service.NotificationServiceImpl;
import com.pawn.community.service.SecommentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:  * 评论
 *
 * @param // FIXME: 2020/10/29
 * @author:美茹冠玉
 * @Return
 * @date 2020/10/29 22:02
 */
@RestController
@Slf4j
public class CommentController {
    @Autowired
    CommentServiceImpl commentInsert;
    @Autowired
    NotificationServiceImpl notificationService;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ArticleDetailsDTOMapper detailsDTOMapper;
    @Autowired
    SecommentMapper secommentMapper;
    @Autowired
    SecommentServiceImpl secommentService;
    int status = 0;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        //apache.commons.lang3.StringUtils;:StringUtils
        if (commentCreateDTO == null || commentCreateDTO.getContent() == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0);
//        //添加评论
//        commentInsert.insert(comment);
        //添加评论
        if (comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == 0 && CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.QUESTION.getType()) {
            // 问题
            List<Comment> commentDB = commentMapper.getCommentById(comment.getParentId());
            if (commentDB == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            //添加文章回复
            commentMapper.commentInsert(comment);
            //添加回复条数
            detailsDTOMapper.incCommentCount(comment.getParentId());
            log.error("comment-getParentId {}", comment.getParentId());
        } else {
            // 查询评论属性
            ArticleDetailsDTO questionDB = detailsDTOMapper.getById(comment.getParentId());
            if (questionDB == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            Map map = new HashMap();
            map.put("capacity", commentCreateDTO.getContent());
            map.put("gmtCreate", System.currentTimeMillis());
            map.put("commentId", commentCreateDTO.getParentId());
            map.put("peopleId", user.getId());
            secommentMapper.addSecondaryComment(map);

        }
//        List<Secomment> secomments = secommentService.SelSecondaryComment(commentCreateDTO.getParentId());

        if (user.getId() == commentCreateDTO.getType()) {
            status = 0;
        }
        //添加评论状态
        Notification notification = new Notification(user.getId(), commentCreateDTO.getType(), commentCreateDTO.getParentId(), NotificationTypeEnum.REPLY_QUESTION.getType(), System.currentTimeMillis(), status);
        notificationService.insertStatus(notification);
        return ResultDTO.okOf();
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO comments(@PathVariable(name = "id") Integer id) {
        List<Secomment> secomments = secommentService.SelSecondaryComment(id);
        return null;
    }
}
