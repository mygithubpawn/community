package com.pawn.community.controller;

import com.pawn.community.dto.NotificationDTO;
import com.pawn.community.dto.PaginationDTO;
import com.pawn.community.mapper.UserMapper;
import com.pawn.community.pojo.Notification;
import com.pawn.community.pojo.User;
import com.pawn.community.dto.ArticleDetailsDTO;
import com.pawn.community.service.ArticleDetailsDTOServiceImpl;
import com.pawn.community.service.ArticleServiceImpl;
import com.pawn.community.service.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * description:  个人中心
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/6 16:35
 */
@Controller
public class ProfileController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleServiceImpl articleService;
    @Autowired
    ArticleDetailsDTOServiceImpl detailsDTOService;
    @Autowired
    NotificationServiceImpl notificationService;

    @GetMapping("/profile/{action}")
    public String Profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "8") Integer size,
                          Model model) {

        //判断当前登陆状态==>已由拦截器接管
        PaginationDTO paginationDTO = new PaginationDTO();

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("pawn".equals(action)) {
            model.addAttribute("section", "pawn");
            model.addAttribute("sectionName", "文章中心");
            //用户的所有记录条数
            Integer integer = articleService.WhereCount(user.getId());
            //分页处理对象
            paginationDTO.setPaginagtion(integer, page, size);
            //位置偏移
            page = size * (page - 1);
            //携带数据
            Map<String, Integer> map = new HashMap();
            map.put("page", page);
            map.put("size", size);
            map.put("userId", user.getId());
            List<ArticleDetailsDTO> articleDetailsDTOS = detailsDTOService.ArticleDetailsWhereList(map);
            //用户文章
            model.addAttribute("articleWhereDe", articleDetailsDTOS);
            //分页
            model.addAttribute("pages", paginationDTO);
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
            //获取回复条数
            Integer IfiInteger = notificationService.inSumId(user.getId());
//            PaginationDTO paginationDTO = new PaginationDTO();
            paginationDTO.setPaginagtion(IfiInteger, page, size);
            //位置偏移
            page = size * (page - 1);
            //携带数据
            Map<String, Integer> map = new HashMap<>();
            map.put("page", page);
            map.put("size", size);
            map.put("userId", user.getId());
            List<NotificationDTO> ReplyToReminder = notificationService.replyToReminder(map);

            Integer ToBeViewed = notificationService.ViewNumber(user.getId());
            //最新回复
            model.addAttribute("ReplyToReminder", ReplyToReminder);
            //分页
            model.addAttribute("pages", paginationDTO);
            //未查看的消息
        }
        return "profile";
    }
}
