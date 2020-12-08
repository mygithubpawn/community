package com.pawn.community.controller;

import com.pawn.community.dto.ArticleDetailsDTO;
import com.pawn.community.dto.PaginationDTO;
import com.pawn.community.schedule.HotTagCache;
import com.pawn.community.service.ArticleDetailsDTOServiceImpl;
import com.pawn.community.service.ArticleServiceImpl;
import com.pawn.community.service.DetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: 首页
 *
 * @param
 * @author:美茹冠玉
 * @Return
 * @date 2020/11/10 21:50
 */
@Controller
@Slf4j
public class IndexController {
    @Autowired
    private DetailsServiceImpl detailsService;
    @Autowired
    private ArticleDetailsDTOServiceImpl detailsDTOService;
    @Autowired
    private ArticleServiceImpl articleService;
    @Autowired
    private HotTagCache hotTagCache;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "title", required = false) String title,
                        @RequestParam(name = "tag", required = false) String tag,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        List<String> tags = hotTagCache.getHots();

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
            model.addAttribute("title", title);
            //分页
            model.addAttribute("pages", paginationDTO);

        } else {
            log.error("title ==>{}", title);
            //获取所有的记录条数
            Integer totalCount = articleService.count();
            //分页处理对象
            PaginationDTO paginationDTO = new PaginationDTO();
            paginationDTO.setPaginagtion(totalCount, page, size);

            page = size * (page - 1);

            //判断当前登陆状态==>已由拦截器接管
            Map<String, Integer> map = new HashMap<>();
            map.put("page", page);
            map.put("size", size);
            List<ArticleDetailsDTO> articleDetailsDTOS = detailsDTOService.ArticleDetailsList(map);
//            articleDetailsDTO = articleDetailsDTOS;
            //文章类容
            model.addAttribute("articleDetailsDTOS", articleDetailsDTOS);
            //分页
            model.addAttribute("pages", paginationDTO);
            //热门话题
            model.addAttribute("tags", tags);

        }
        if (tag != null) {
            Map mapTag = new HashMap<>();
            mapTag.put("page", page);
            mapTag.put("size", size);
            mapTag.put("tag", tag);
            List<ArticleDetailsDTO> articleDetailsDTOS = detailsDTOService.SelectTag(mapTag);
            //分页处理对象
            Integer totalCount = articleService.SelectPopular(tag);
            PaginationDTO paginationDTO = new PaginationDTO();
            paginationDTO.setPaginagtion(totalCount, page, size);

            page = size * (page - 1);

            //分页
            model.addAttribute("pages", paginationDTO);
            model.addAttribute("tag", tag);
            //文章类容
            model.addAttribute("articleDetailsDTOS", articleDetailsDTOS);
        }
        log.error("tag==>{}", tag);
        return "index";
    }
}
