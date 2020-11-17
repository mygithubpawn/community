package com.pawn.community.service;

import com.pawn.community.controller.CommentController;
import com.pawn.community.dto.ArticleDetailsDTO;
import com.pawn.community.dto.CommentCreateDTO;
import com.pawn.community.dto.CommentDTO;
import com.pawn.community.enums.CommentTypeEnum;
import com.pawn.community.exception.CustomizeErrorCode;
import com.pawn.community.exception.CustomizeException;
import com.pawn.community.mapper.ArticleDetailsDTOMapper;
import com.pawn.community.mapper.CommentMapper;
import com.pawn.community.mapper.SecommentMapper;
import com.pawn.community.pojo.Comment;
import com.pawn.community.pojo.Secomment;
import com.pawn.community.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ArticleDetailsDTOMapper detailsDTOMapper;
    @Autowired
    SecommentMapper secommentMapper;

    @Transactional
    public void insert(Comment comment) {
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

        } else {
            // 查询评论属性
            ArticleDetailsDTO questionDB = detailsDTOMapper.getById(comment.getParentId());
            if (questionDB == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            Map map=new HashMap();
            map.put("capacity",comment.getContent());
            map.put("gmtCreate",System.currentTimeMillis());
            map.put("commentId",comment.getId());
            map.put("peopleId",2);
            secommentMapper.addSecondaryComment(map);

        }
    }


    public List<CommentDTO> listByQuestionId(Integer id) {
        //commentMapper.selectByExample(new )
        Map<String, Integer> map = new HashMap();
        map.put("articleId", id);
        List<CommentDTO> commentMapperDTO = commentMapper.SelectCreate(map);

        //1，获取去重的评论人
        Set<CommentDTO> commentSet = commentMapperDTO.stream().distinct().collect(Collectors.toSet());

        //2，获取评论人并转为map
        Map<Integer, CommentDTO> userMap1 = commentSet.stream().collect(Collectors.toMap(User -> User.getId(), CommentDTO -> CommentDTO));

        //3，转换CommentDTO为commentCreateDTO
        List<CommentDTO> commentDTOS = commentMapperDTO.stream().map(comment1 -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment1, commentDTO);
//            commentDTO.setUser(userMap1.get(comment1.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }

}
