package com.pawn.community.controller;

import com.pawn.community.dto.CommentCreateDTO;
import com.pawn.community.dto.CommentDTO;
import com.pawn.community.mapper.UserMapper;
import com.pawn.community.pojo.*;
import com.pawn.community.dto.ArticleDetailsDTO;
import com.pawn.community.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 文章详情
 */
@Controller
public class DetailsArDeController {
    @Autowired
    ArticleDetailsDTOServiceImpl articleDetailsDTOService;
    @Autowired
    DetailsServiceImpl detailsService;
    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    ArticleServiceImpl articleService;
    @Autowired
    NotificationServiceImpl notificationService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    SecommentServiceImpl secommentService;

    @GetMapping("/question/{id}")
    public String question(HttpServletRequest request,
                           @PathVariable("id") Integer id,
                           Model model) {
        List<Secomment> secomments = null;
        //判断当前登陆状态==>已由拦截器接管
        User user = (User) request.getSession().getAttribute("user");

        Map<String, Integer> map = new HashMap<>();
        map.put("articleId", id);
        ArticleDetailsDTO articleDetailsDTO = articleDetailsDTOService.gitById(map);
        //获取评论属性
        List<CommentDTO> commentCreateDTOS = commentService.listByQuestionId(id);
        //累计添加阅读数
        detailsService.incView(id);
        List<Article> articles = articleService.relatedrtAicles(articleDetailsDTO.getTag(), id);

        List<Comment> articleComments = detailsService.ArticleComments(id);
        for (Comment articleComment : articleComments) {
            secomments = secommentService.SelSecondaryComment(articleComment.getId());
        }
        /**
         * 查询相关问题
         */
        model.addAttribute("articles", articles);

        model.addAttribute("articleComments", articleComments);

        model.addAttribute("secomments", secomments);
        /**
         *
         * 文章详情
         */
        model.addAttribute("ArticleDetails", articleDetailsDTO);
        /**
         * 文章评论
         */
        model.addAttribute("commentCreateDTOS", commentCreateDTOS);

        return "question";
    }
}

