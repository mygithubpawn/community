package com.pawn.community.mapper;

import com.pawn.community.pojo.Comment;
import com.pawn.community.pojo.Details;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/***
 * description:  *文章附加数据详情
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/6 16:00
 */
@Mapper
@Repository
public interface DetailsMapper {

    /**
     * 查询当前文章的相关属性（未被使用）
     *
     * @param id
     * @return
     */
    @Select("select * from details where id=#{id}")
    Details DetailsList(@Param("id") Integer id);

    /**
     * 添加对应的文章属性
     *
     * @param details
     * @return
     */
    @Insert("insert into details(id,comment_count,view_count,like_count,reply_count)" +
            " values (#{id},#{commentCount},#{viewCount},#{likeCount},#{replyCount})")
    int addToDetails(Details details);

    /**
     * 累计增加浏览数
     *
     * @param id
     * @return
     */
    @Update("UPDATE details SET view_count=view_count+1 WHERE id=#{id}")
    int modifyBrowse(@Param("id") Integer id);

    /***
     * description: 查询热门数据
     * @author:美茹冠玉
     * @Return java.util.List<com.pawn.community.pojo.Details>
     * @param
     * @date 2020/11/11 16:56
     */
    @Select("select comment_count ,view_count,like_count,reply_count from details where id=#{id} ")
    List<Details> PopularData(Integer id);

    /**
     * 查询当前文章的所有评论id
     * @param id
     * @return
     */
    @Select("select id from comment where parent_id=#{id}")
    List<Comment> ArticleComments(Integer id);
}
