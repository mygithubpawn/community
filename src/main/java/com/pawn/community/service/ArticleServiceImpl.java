package com.pawn.community.service;

import com.pawn.community.exception.CustomizeErrorCode;
import com.pawn.community.exception.CustomizeException;
import com.pawn.community.mapper.ArticleMapper;
import com.pawn.community.mapper.DetailsMapper;
import com.pawn.community.pojo.Article;
import com.pawn.community.pojo.Details;
import com.pawn.community.service.impl.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    DetailsServiceImpl detailsService;
    @Autowired
    DetailsMapper detailsMapper;

    @Override
    public int insertArticle(Article article) {
        return articleMapper.insertArticle(article);
    }

    @Override
    public Integer SelectPopular(String tag) {
        return articleMapper.SelectPopular(tag);
    }

    @Override
    public Integer count() {
        return articleMapper.count();
    }

    @Override
    public Integer SelectCount(String title) {
        return articleMapper.SelectCount(title);
    }

    @Override
    public Integer WhereCount(Integer userId) {
        return articleMapper.WhereCount(userId);
    }

    @Override
    public Article PublishId(Integer id) {
        Article article = articleMapper.PublishId(id);
        //优雅的定义异常
        if (article == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }

        return article;
    }
    @Override
    public List<Article> relatedrtAicles(String tag,Integer id) {
        return articleMapper.relatedrtAicles(tag,id);
    }

    @Transactional
    public void createOrUpdate(Article article) {
        if (article.getId() == null) {
            //添加
            articleMapper.insertArticle(article);
            //获取当前表的最大id==>当前文章id
            int articleMaxId = articleMapper.DetailsId();
            //添加对应的文章属性
            detailsService.addToDetails(new Details(articleMaxId, 0, 0, 0, 0));

        } else {
            //跟新
            int update = articleMapper.update(article);
            if (update != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
