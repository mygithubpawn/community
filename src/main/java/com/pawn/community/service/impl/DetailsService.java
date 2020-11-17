package com.pawn.community.service.impl;

import com.pawn.community.pojo.Comment;
import com.pawn.community.pojo.Details;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DetailsService {
    Details DetailsList(Integer id);

    int addToDetails(Details details);
    List<Details> PopularData(Integer id);
    List<Comment> ArticleComments(Integer id);
}
