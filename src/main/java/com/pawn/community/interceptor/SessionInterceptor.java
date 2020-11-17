package com.pawn.community.interceptor;

import com.pawn.community.mapper.UserMapper;
import com.pawn.community.pojo.User;
import com.pawn.community.service.AdService;
import com.pawn.community.service.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

/**
 * 拦截器实现
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    UserMapper userMapper;
    @Autowired
    NotificationServiceImpl notificationService;
    @Autowired
    AdService adService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)
    {
        //广告
        request.getSession().setAttribute("ads", adService.list());
        //程序处理之前
        //判断当前登陆状态
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                //获取前端的token
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    //数据库查询是否存在token
                    User user = userMapper.finByToken(token);
                    //把user放入Session
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        Integer ToBeViewed = notificationService.ViewNumber(user.getId());
                        request.getSession().setAttribute("ToBeViewed", ToBeViewed);
                    }
                    break;
                }
            }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
