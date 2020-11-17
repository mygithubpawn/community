package com.pawn.community.pojo;

import lombok.Data;

/***
 * description: 二级评
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/9 19:57
 */
@Data
public class Secomment {
    /**
     * 二级评论的id
     */
    private Integer id;
    /**
     * 评论类容
     */
    private String capacity;
    /**
     * 二级评论时间
     */
    private Long gmtCreate;
    /**
     * 该评论的id
     */
    private Integer commentId;

    /**
     * 当前评论人的id
     */
    private Integer peopleId;
}
