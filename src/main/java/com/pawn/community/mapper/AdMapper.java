package com.pawn.community.mapper;

import com.pawn.community.pojo.Ad;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * description:  * 广告接口
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/12 15:16
 */
@Mapper
@Repository
public interface AdMapper {
    /**
     * 查询所有的广告
     *
     * @return
     */
    @Select("select title,url,image,gmt_start,gmt_end,gmt_create,gmt_modified,pos " +
            "from ad where ad.status=#{status}")
    List<Ad> SelectAd(Integer status);
}
