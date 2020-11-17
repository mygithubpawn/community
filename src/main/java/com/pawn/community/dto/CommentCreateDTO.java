package com.pawn.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * description:  * 二级评论
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/10/29 22:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("CommentCreateDTO")
public class CommentCreateDTO {
    /**
     * id
     */
    private Integer  parentId;
    /**
     * 类容
     */
    private String  content;
    /**
     * 父类的类型
     */
    private Integer type;

}
