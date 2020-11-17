package com.pawn.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * description:  * 文章表
 *
 * @param
 * @author:美茹冠玉
 * @Return
 * @date 2020/10/29 22:18
 */
@Data
@Alias("Article")
@AllArgsConstructor
public class Article {
    /**
     * 文章自增id
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 文章发布的时间
     */
    private Long gmtCreate;
    /**
     * 创建人的id
     */
    private Integer creator;
    /**
     * 标签
     */
    private String tag;

    public Article(String title, String description, Integer creator, Long gmtCreate, String tag, Integer id) {
        this.title = title;
        this.description = description;
        this.tag = tag;
        this.creator = creator;
        this.id = id;
        this.gmtCreate = gmtCreate;
    }

    public Article(Integer id, String title, String tag) {
        this.id = id;
        this.title = title;
        this.tag = tag;
    }

    public Article(Integer id,String tag) {
        this.id = id;
        this.tag = tag;
    }
}
