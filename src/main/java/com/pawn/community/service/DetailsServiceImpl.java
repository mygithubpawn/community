package com.pawn.community.service;

import com.pawn.community.mapper.DetailsMapper;
import com.pawn.community.pojo.Comment;
import com.pawn.community.pojo.Details;
import com.pawn.community.service.impl.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DetailsServiceImpl implements DetailsService {
    @Autowired
    DetailsMapper detailsMapper;

    @Override
    public Details DetailsList(Integer id) {
        return detailsMapper.DetailsList(id);
    }

    @Override
    public int addToDetails(Details details) {
        return detailsMapper.addToDetails(details);
    }

    @Override
    public List<Details> PopularData(Integer id) {
        return detailsMapper.PopularData(id);
    }

    @Override
    public List<Comment> ArticleComments(Integer id) {
        return detailsMapper.ArticleComments(id);
    }

    @Transactional
    public void incView(Integer id) {
        //浏览量加一
        detailsMapper.modifyBrowse(id);

    }
}
