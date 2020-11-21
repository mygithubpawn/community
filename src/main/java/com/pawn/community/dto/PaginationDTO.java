package com.pawn.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/***
 * description: 分页
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/7 15:19
 */
@Data
public class PaginationDTO {
    private List<ArticleDetailsDTO> detailsDTOS;
    /**
     * 是否展示向前箭头
     */
    private Boolean showPrevious;
    /**
     * 首页按钮
     */
    private Boolean showFirstPage;
    /**
     * 是否展示向后箭头
     */
    private Boolean showNext;
    /**
     * 尾页按钮
     */
    private Boolean showEndPage;
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 当前配置数
     */
    private List<Integer> pages = new ArrayList<>();

    private Integer totalPage;

    public void setPaginagtion(Integer totalCount, Integer page, Integer size) {

//        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //修复容错
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        // 是否展示上一页
        showPrevious = (page == 1) ? false : true;
        //是否展示下一页
        showNext = (page == totalPage) ? false : true;
        //是否展示第一页
        showFirstPage = (pages.contains(1)) ? false : true;
        //是否展示最后一页
        showEndPage = (pages.contains(totalPage)) ? false : true;
    }
}
