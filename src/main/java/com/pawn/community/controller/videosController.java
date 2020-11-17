package com.pawn.community.controller;

import com.pawn.community.mapper.RelatedVideoMapper;
import com.pawn.community.pojo.RelatedVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/***
 * description: 视频跳转
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/12 21:57
 */
@Controller
public class videosController {
    @Autowired
    RelatedVideoMapper relatedVideoMapper;

    @GetMapping("/videos")
    public String videoJump(
                            Model model) {
        List<RelatedVideo> relatedVideos = relatedVideoMapper.AllVideos();
        model.addAttribute("relatedVideos", relatedVideos);
        return "videos";
    }

    @GetMapping("/video")
    public String videoDetails(@RequestParam(name = "aid") String aid,
                               @RequestParam(name = "page") String page,
                               Model model) {
        model.addAttribute("aid", aid);
        model.addAttribute("page", page);
        return "video";
    }
}
