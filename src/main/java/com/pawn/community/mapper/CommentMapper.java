package com.pawn.community.mapper;

import com.pawn.community.dto.CommentDTO;
import com.pawn.community.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/***
 * description: 回复类容表操作
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/6 15:44
 */
@Mapper
@Repository
public interface CommentMapper {
    /**
     * 添加一级评论
     *
     * @param comment
     * @return
     */
    @Insert("insert into comment(parent_id,type,commentator,gmt_create,gmt_modified,like_count,content)" +
            "values(#{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModified},#{likeCount},#{content})")
    int commentInsert(Comment comment);

    /**
     * 查询当前的评论类容的id
     *
     * @param parentId
     * @return
     */
    //查询
    @Select("select id from comment where parent_id=#{parentId}")
    List<Comment> getCommentById(Integer parentId);

    /**
     * 查询当前文章的没所有评论
     *
     * @param map
     * @return
     */
    List<CommentDTO> SelectCreate(Map<String, Integer> map);
}
