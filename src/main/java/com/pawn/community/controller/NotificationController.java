package com.pawn.community.controller;

import com.pawn.community.pojo.Notification;
import com.pawn.community.pojo.User;
import com.pawn.community.service.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 * description: 控制评论状态
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/8 19:56
 */
@Controller
public class NotificationController {
    @Autowired
    NotificationServiceImpl notificationService;
    @GetMapping("/notification/{id}")
    public String NotificationStatus(@PathVariable(name = "id") Integer id, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }
        List<Notification> statusList = notificationService.checkStatus(id);
        for (Notification notification : statusList) {
            if (notification.getStatus()==0){
                //修改评论属性为已读
                notificationService.notViewed(id);
            }
        }
        return "redirect:/question/"+id;

    }
}
