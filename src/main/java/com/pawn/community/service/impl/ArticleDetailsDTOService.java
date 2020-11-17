package com.pawn.community.service.impl;

import com.pawn.community.dto.ArticleDetailsDTO;

import java.util.List;
import java.util.Map;

public interface ArticleDetailsDTOService {
    List<ArticleDetailsDTO> ArticleDetailsList(Map<String ,Integer>map);
    List<ArticleDetailsDTO> SelectIArticle(Map<String, Integer> map);
    List<ArticleDetailsDTO> ArticleDetailsWhereList(Map<String ,Integer>map);
    List<ArticleDetailsDTO> SelectTag(Map map);
    ArticleDetailsDTO gitById(Map<String,Integer>map);
}
