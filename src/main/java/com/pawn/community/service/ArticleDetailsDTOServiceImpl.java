package com.pawn.community.service;

import com.pawn.community.exception.CustomizeErrorCode;
import com.pawn.community.exception.CustomizeException;
import com.pawn.community.mapper.ArticleDetailsDTOMapper;
import com.pawn.community.mapper.ArticleMapper;
import com.pawn.community.dto.ArticleDetailsDTO;
import com.pawn.community.service.impl.ArticleDetailsDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleDetailsDTOServiceImpl implements ArticleDetailsDTOService {
    @Autowired
    ArticleDetailsDTOMapper dtoMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Override
    public List<ArticleDetailsDTO> ArticleDetailsList(Map<String ,Integer> map){
        return dtoMapper.ArticleDetailsList(map);

    }

    @Override
    public List<ArticleDetailsDTO> SelectIArticle(Map map) {
        return dtoMapper.SelectIArticle(map);
    }

    @Override
    public List<ArticleDetailsDTO> ArticleDetailsWhereList( Map<String, Integer> map) {
        return dtoMapper.ArticleDetailsWhereList(map);
    }

    @Override
    public List<ArticleDetailsDTO> SelectTag(Map map) {
        return dtoMapper.SelectTag(map);
    }

    @Override
    public ArticleDetailsDTO gitById(Map<String, Integer> map) {
        ArticleDetailsDTO articleDetailsDTO = dtoMapper.gitById(map);
        //异常捕获
        if (articleDetailsDTO.getId()==null){
            throw new CustomizeException(CustomizeErrorCode.INVALID_OPERATION);
        }
        return articleDetailsDTO;
    }

}
