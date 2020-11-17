package com.pawn.community.mapper;

import com.pawn.community.dto.ArticleDetailsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ArticleDetailsDTOMapper {
    /**
     * 查询所有的用户文章
     *
     * @param map
     * @return
     */
    List<ArticleDetailsDTO> ArticleDetailsList(Map<String, Integer> map);

    /**
     * 实现搜索功能
     *
     * @param map
     * @return
     */
    List<ArticleDetailsDTO> SelectIArticle(Map map);

    /**
     * 实现热门话题
     *
     * @param map
     * @return
     */
    List<ArticleDetailsDTO> SelectTag(Map map);

    /**
     * 查询当前用户的所有文章
     *
     * @param map
     * @return
     */
    List<ArticleDetailsDTO> ArticleDetailsWhereList(Map<String, Integer> map);

    /**
     * 查询当前文章的详情
     *
     * @param map
     * @return
     */
    ArticleDetailsDTO gitById(Map<String, Integer> map);

    /**
     * 按id查询
     *
     * @param parentId
     * @return
     */
    @Select("select * from comment where id=#{parentId}")
    ArticleDetailsDTO getById(Integer parentId);

    /**
     * 回复数加一
     *
     * @param id
     * @return
     */
    @Update("UPDATE details SET reply_count=reply_count+1 WHERE id=#{id}")
    int incCommentCount(Integer id);
}
