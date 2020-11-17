package com.pawn.community.service.impl;

import com.pawn.community.pojo.Article;

import java.util.List;

public interface ArticleService {


    int insertArticle(Article article);
    Integer SelectPopular(String tag);
    Integer count();
    Integer SelectCount(String title);

    Integer WhereCount(Integer userId);

    Article PublishId(Integer id);

    List<Article> relatedrtAicles(String tag,Integer id);
}
