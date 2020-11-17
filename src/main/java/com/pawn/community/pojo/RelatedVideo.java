package com.pawn.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * description: 相关视频
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/13 16:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatedVideo {
    /**
     * 视频id
     */
    private Integer id;
    /**
     * 视频封面
     */
    private String url;
    /**
     * 视频标识符page
     */
    private String page;
    /**
     * 视频标识符aid
     */
    private String aid;
    /**
     * 视频发布人
     */
    private String name;
    /**
     * 发布人的地址
     */
    private String address;
    /**
     * 视频标题
     */
    private String heading;
    /**
     * 视频发布的时间
     */
    private Long gmtCreate;
    /**
     * 视频是否有多条数据
     */
    private Integer identification;
}
