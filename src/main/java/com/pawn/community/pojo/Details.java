package com.pawn.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * description: 文章属性表
 * @author:美茹冠玉
 * @Return 
 * @param 
 * @date 2020/10/29 22:18
 */
@Data
@Alias("Details")
@AllArgsConstructor
public class Details {
    /**
     * id与creator关联
     */
    private Integer id;
    /**
     * 关注数量
     */
    private Integer commentCount;
    /**
     * 浏览数量
     */
    private Integer viewCount;
    /**
     * 点赞数量
     */
    private Integer likeCount;
    /**
     * 回复
     */
    private Integer replyCount;

    public Details(Integer commentCount, Integer viewCount, Integer likeCount, Integer replyCount) {
        this.commentCount = commentCount;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.replyCount = replyCount;
    }

    public Details(Integer id) {
        this.id = id;
    }
}
