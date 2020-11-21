package com.pawn.community.controller;

import com.pawn.community.dto.ArticleDetailsDTO;
import com.pawn.community.dto.PaginationDTO;
import com.pawn.community.schedule.HotTagCache;
import com.pawn.community.service.ArticleDetailsDTOServiceImpl;
import com.pawn.community.service.ArticleServiceImpl;
import com.pawn.community.service.DetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * description: 项目后台
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/20 14:55
 */
@Controller
public class ManagementController {
    @Autowired
    private ArticleDetailsDTOServiceImpl detailsDTOService;
    @Autowired
    private ArticleServiceImpl articleService;
    @GetMapping("/management")
    public String management(  Model model,
                               @RequestParam(name = "title", required = false) String title,
                               @RequestParam(name = "page", defaultValue = "1") Integer page,
                               @RequestParam(name = "size", defaultValue = "10") Integer size){

        if (title != null && title != "") {
            //获取所有的记录条数
            Integer totalCount = articleService.SelectCount(title);
            PaginationDTO paginationDTO = new PaginationDTO();
            paginationDTO.setPaginagtion(totalCount, page, size);
            page = size * (page - 1);
            Map map = new HashMap<>();
            map.put("page", page);
            map.put("size", size);
            map.put("title", title);
            List<ArticleDetailsDTO> articleDetailsDTOS = detailsDTOService.SelectIArticle(map);
//            articleDetailsDTO = articleDetailsDTOS;
            //文章类容
            model.addAttribute("articleDetailsDTOS", articleDetailsDTOS);
            //分页
            model.addAttribute("pages", paginationDTO);
        }else {
        //获取所有的记录条数
        Integer totalCount = articleService.count();
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPaginagtion(totalCount, page, size);
        page = size * (page - 1);
        Map<String, Integer> map = new HashMap<>();
        map.put("page", page);
        map.put("size", size);
        List<ArticleDetailsDTO> articleDetailsDTOS = detailsDTOService.ArticleDetailsList(map);
        //文章类容
        model.addAttribute("articleDetailsDTOS", articleDetailsDTOS);
        //分页
        model.addAttribute("pages", paginationDTO);

        }
        return "management";
    }
}
