package com.pawn.community.pojo;

import lombok.Data;

/***
 * description: 广告实体类
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/12 15:17
 */
@Data
public class Ad {
    /**
     * 广告id
     */
    private Integer id;
    /**
     * 广告标题
     */
    private String title;
    /**
     * 广告地址
     */
    private String url;
    /**
     * 广告图片
     */
    private String image;
    /**
     * 广告开始时间
     */
    private Long gmtStart;
    /**
     * 广告结束时间
     */
    private Long gmtEnd;
    /**
     * 广告上架时间
     */
    private Long gmtCreate;
    /**
     * 广告下架时间
     */
    private Long gmtModified;
    /**
     * 广告状态
     */
    private Integer status;
    /**
     * 广告位置
     */
    private String pos;

    public Ad(String title, String url, String image, Long gmtStart, Long gmtEnd, Long gmtCreate, Long gmtModified, String pos) {
        this.title = title;
        this.url = url;
        this.image = image;
        this.gmtStart = gmtStart;
        this.gmtEnd = gmtEnd;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.pos = pos;
    }
}
