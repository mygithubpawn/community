package com.pawn.community.service;

import com.pawn.community.mapper.SecommentMapper;
import com.pawn.community.pojo.Secomment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 二级评论业务层
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/9 20:16
 */

@Service
public class SecommentServiceImpl {

    @Autowired
    SecommentMapper secommentMapper;

     public List<Secomment> SelSecondaryComment(Integer id){
        return secommentMapper.SelSecondaryComment(id);
    }

}
