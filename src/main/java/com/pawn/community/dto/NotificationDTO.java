package com.pawn.community.dto;

import com.pawn.community.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * description: 最新回复DTO
 *
 * @param
 * @author:美茹冠玉
 * @Return
 * @date 2020/11/7 11:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("NotificationDTO")
public class NotificationDTO {
    /**
     * 该评论的id
     */
    private Integer notifier;
    /**
     * 回复的类型
     */
    private String type;
    /**
     * 判断回复是否查看（默认为0，表示未查看）
     */
    private Integer status;
    /**
     * 回复时间
     */
    private Long gmtCreate;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章发布人的id
     */
    private Integer creator;
    /**
     * 当前文章的id
     */
    private Integer id;
    /**
     * 回复类容
     */
    private String content;
    /**
     * 评论者的id
     */
    private Integer userId;
    /**
     * 评论者的用户名
     */
    private String userName;
}
