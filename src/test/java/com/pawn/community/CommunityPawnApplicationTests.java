package com.pawn.community;

import com.pawn.community.controller.CommentController;
import com.pawn.community.mapper.*;
import com.pawn.community.pojo.Article;
import com.pawn.community.pojo.Comment;
import com.pawn.community.pojo.Details;
import com.pawn.community.pojo.Secomment;
import com.pawn.community.schedule.HotTagCache;
import com.pawn.community.service.ArticleDetailsDTOServiceImpl;
import com.pawn.community.service.ArticleServiceImpl;
import com.pawn.community.service.CommentServiceImpl;
import com.pawn.community.service.DetailsServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
class CommunityPawnApplicationTests {
    @Autowired
    ArticleDetailsDTOServiceImpl dtoService;
    @Autowired
    ArticleDetailsDTOMapper detailsDTOMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleServiceImpl articleService;
    @Autowired
    DetailsServiceImpl detailsService;
    @Autowired
    DetailsMapper detailsMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    NotificationMapper notificationMapper;
    @Autowired
    SecommentMapper secommentMapper;
    @Autowired
    HotTagCache hotTagCache;

    @Test
    void contextLoads() {
//        Map map=new HashMap();
//        map.put("page",0);h
//        map.put("size",5);
//        List<ArticleDetailsDTO> articleDetailsDTOS = dtoService.ArticleDetailsList(map);
//        articleDetailsDTOS.forEach(s-> System.out.println(s));
//        User user=new User();
//        Map map=new HashMap();
//        map.put("page",0);
//        map.put("size",5);
//        map.put("userId",userMapper.findUserId().getId());
//        List<ArticleDetailsDTO> articleDetailsDTOS = dtoService.ArticleDetailsWhereList(map);
//        articleDetailsDTOS.forEach(s-> System.out.println(s));
//        List<ArticleDetailsDTO> articleDetailsDTOS1 = detailsDTOMapper.ArticleDetailsList();
//        articleDetailsDTOS1.forEach(s-> System.out.println(s));
//        detailsDTOMapper.seltr().forEach(s-> System.out.println(s));
//        Map map=new HashMap();
//        map.put("articleId",2);
//        System.out.println(detailsDTOMapper.gitById(map));
//        System.out.println(articleService.PublishId(1));
//        int i = detailsService.addToDetails(new Details(2,1, 1, 1, 1));
//        System.out.println(i);
//        System.out.println(articleMapper.DetailsId());detailsMapper
//        System.out.println(detailsMapper.modifyBrowse(1));
//        System.out.println(commentMapper.commentInsert(new Comment(2, 3, 4, System.currentTimeMillis(), System.currentTimeMillis(), 7, "r34")));
//        Map map=new HashMap();
//        map.put("articleId",1);
//        commentMapper.SelectCreate(map).forEach(s-> System.out.println(s));
//        List<Article> articles = articleMapper.relatedrtAicles(9);
//        articles.forEach(s-> System.out.println(s));
//        Map<String,Integer> map=new HashMap();
////        map.put("userId",1);
////        List list = notificationMapper.replyToReminder(map);
////        list.forEach(s-> System.out.println(s));
//        Secomment secomment=new Secomment();
//        secomment.setCapacity("杜甫");
//        secomment.setGmt_create(System.currentTimeMillis());
//        secomment.setComment_id(2);
//        secomment.setPeople_id(1);
//        Map map=new HashMap();
//        map.put("capacity","杜甫");
//        map.put("gmtCreate",45432342);
//        map.put("commentId",1);
//        map.put("peopleId",2);
//        secommentMapper.addSecondaryComment(map);
//        Map map = new HashMap<>();
//        map.put("page",0);
//        map.put("size", 5);
//        map.put("title","标");
//        dtoService.SelectIArticle(map).forEach(s-> System.out.println(s));

//        detailsMapper.ArticleComments(1).forEach(s-> System.out.println(s));
//        articleMapper.relatedrtAicles(4).forEach(s-> System.out.println(s));
    }
}
