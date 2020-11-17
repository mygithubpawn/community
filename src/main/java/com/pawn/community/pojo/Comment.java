package com.pawn.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * description:  * 文章回复表
 *
 * @param
 * @author:美茹冠玉
 * @Return
 * @date 2020/10/29 22:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("Comment")
public class Comment {
    /**
     * 唯一标识符(评论的id)
     */
    private Integer id;
    /**
     * 父类id
     */
    private Integer parentId;
    /**
     * 父类的类型
     */
    private Integer type;
    /**
     * 评论人的id
     */
    private Integer commentator;
    /**
     * 创建人的时间
     */
    private Long gmtCreate;
    /**
     * 更新时间
     */
    private Long gmtModified;
    /**
     * 点赞数
     */
    private Integer likeCount;
    /**
     * 回复类容
     */
    private String content;

    public Comment(Integer parentId, Integer type, Integer commentator, Long gmtCreate, Long gmtModified, Integer likeCount, String content) {
        this.parentId = parentId;
        this.type = type;
        this.commentator = commentator;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.likeCount = likeCount;
        this.content = content;
    }

    public Comment(Integer id) {
        this.id = id;
    }
}
