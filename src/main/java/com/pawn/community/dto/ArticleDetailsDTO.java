package com.pawn.community.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 文章列表显示
 */
@Data
@Alias("ArticleDetailsDTO")
public class ArticleDetailsDTO implements Serializable {
    /**
     * 文章作者
     */
    private String name;
    /**
     * 文章id
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
     * 标签
     */
    private String tag;
    /**
     * user==>头像
     */
    private String avatarUrl;
    /**
     * details==>关注数量
     */
    private Integer commentCount;
    /**
     * details==>浏览数量
     */
    private Integer viewCount;
    /**
     * details==>点赞数量
     */
    private Integer likeCount;
    /**
     * 文章发布的时间
     */
    private Long gmtCreate;
    /**
     * details==>回复
     */
    private Integer replyCount;
    /**
     * 创建人的id与userId关联
     */
    private Integer creator;

}
