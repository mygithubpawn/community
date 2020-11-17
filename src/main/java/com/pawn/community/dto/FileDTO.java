package com.pawn.community.dto;

import lombok.Data;

/***
 * description: 图片上传DTO
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/9 8:53
 */
@Data
public class FileDTO {
    private int success;
    private String message;
    private String url;
}
