package com.pawn.community.pojo;

import lombok.Data;

import java.util.List;

/***
 * description:  * 标签属性
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/6 13:07
 */
@Data
public class Tag {
    private String categoryName;
    private List<String> Tags;
}
