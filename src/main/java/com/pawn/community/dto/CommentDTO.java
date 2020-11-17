package com.pawn.community.dto;

import com.pawn.community.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/***
 * description:  * 一级评论（评论文章）
 * @author:
 * @Return
 * @param
 * @date 2020/11/2 21:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("CommentDTO")
public class CommentDTO {
    /**
     * 唯一标识符
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
    /**
     * 当前用户数据
     */
    private User user;

}
