package com.pawn.community.service;

import com.pawn.community.dto.NotificationDTO;
import com.pawn.community.dto.PaginationDTO;
import com.pawn.community.mapper.NotificationMapper;
import com.pawn.community.pojo.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/***
 * description: 我的回复Service
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/7 15:17
 */
@Service
public class NotificationServiceImpl {

    @Autowired
    NotificationMapper notificationMapper;

    public void insertStatus(Notification notification) {
        notificationMapper.addStatus(notification);
    }

    public Integer inSumId(Integer id) {
        return notificationMapper.inSumId(id);
    }

    public List<NotificationDTO> replyToReminder(Map<String, Integer> map) {
        return notificationMapper.replyToReminder(map);
    }

    public Integer notViewed(Integer id) {
        return notificationMapper.notViewed(id);
    }

    public List<Notification> checkStatus(Integer id) {
        return notificationMapper.checkStatus(id);
    }

    public Integer ViewNumber(Integer id) {
        return notificationMapper.ViewNumber(id);
    }
}
