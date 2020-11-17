package com.pawn.community.mapper;

import com.pawn.community.pojo.RelatedVideo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * description: 视频Mapper
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/13 16:32
 */
@Mapper
@Repository
public interface RelatedVideoMapper {
    /**
     * 查询所有的视频属性
     *
     * @return
     */
    @Select("select id,url,page,aid,name,address,heading,gmt_create,identification from relatedvideo")
    List<RelatedVideo> AllVideos();
}
