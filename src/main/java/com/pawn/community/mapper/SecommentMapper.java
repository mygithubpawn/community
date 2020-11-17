package com.pawn.community.mapper;

import com.pawn.community.pojo.Secomment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * description: 二级评Mapper
 *
 * @param
 * @author:美茹冠玉
 * @Return
 * @date 2020/11/9 20:10
 */
@Mapper
@Repository
public interface SecommentMapper {
    /**
     * 添加二级评论
     *
     * @param map
     * @return
     */
    @Insert("insert into secomment(capacity,gmt_create,comment_id,people_id)" +
            "values(#{capacity},#{gmtCreate},#{commentId},#{peopleId})")
    Integer addSecondaryComment(Map map);

    /**
     * 查询当前文章的所有二级评论
     * @param id
     * @return
     */
    @Select("SELECT * FROM `secomment` WHERE comment_id=#{id}" +
            " ORDER BY secomment.gmt_create DESC")
    List<Secomment> SelSecondaryComment(Integer id);
}
