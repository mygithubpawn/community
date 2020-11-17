package com.pawn.community.dto;

import lombok.Data;

/***
 * description: 热门排序DTO
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/11 18:45
 */

@Data
public class HotTagDTO implements Comparable{
    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.getPriority()-((HotTagDTO)o).getPriority();
    }
}
