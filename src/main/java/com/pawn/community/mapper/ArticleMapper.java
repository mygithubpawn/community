package com.pawn.community.mapper;

import com.pawn.community.pojo.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>show 方法的详细说明第一行<br>
 * show 文章Mapper
 */
@Mapper
@Repository
public interface ArticleMapper {
    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    @Insert("insert into article(title,description,gmt_create,creator,tag)" +
            " values(#{title},#{description},#{gmtCreate},#{creator},#{tag})")
    int insertArticle(Article article);

    /**
     * 查询文章表的数据条数
     *
     * @return
     */
    @Select("select count(1) from article")
    Integer count();

    /**
     * 查询文章表的数据条数
     *
     * @return
     */
    @Select("select count(1) from article where article.title LIKE \"%\"#{title}\"%\"\n")
    Integer SelectCount(String title);

    /**
     * 查询该热门话题的的数据条数
     *
     * @param tag
     * @return
     */
    @Select("select count(1) from article where article.tag LIKE \"%\"#{tag}\"%\"\n")
    Integer SelectPopular(String tag);

    /**
     * 查询当前用户的文章条数（个人中心）
     *
     * @param userId
     * @return
     */
    @Select("select count(1) from article  where creator=#{userId}")
    Integer WhereCount(@Param("userId") Integer userId);

    /**
     * 重新编辑文章查询该文章数据（用于数据回显）
     *
     * @param id
     * @return
     */
    @Select("select * from article where id=#{id}")
    Article PublishId(@Param("id") Integer id);

    /**
     * 更新当前文章
     *
     * @param article
     * @return
     */
    @Update("update article set title=#{title},description=#{description},tag=#{tag} where id=#{id}")
    int update(Article article);

    /**
     * @return
     */
    @Select("SELECT MAX(id) FROM article")
    int DetailsId();

    /**
     * 相关文章查询
     *
     * @param id
     * @return
     * ' #{tag}'
     */
    @Select("select id,title,tag FROM article WHERE tag REGEXP #{tag} AND id!=#{id}")
    List<Article> relatedrtAicles(String tag,Integer id);

    /***
     * description: 获取所有的标签
     * @author:美茹冠玉
     * @Return java.util.List
     * @param
     * @date 2020/11/11 13:25
     */
    @Select("select id,tag from article")
    List<Article> AllTags();
}
