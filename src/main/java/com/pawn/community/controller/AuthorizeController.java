package com.pawn.community.controller;

import com.pawn.community.dto.AccessTokenDTO;
import com.pawn.community.dto.GithubUser;
import com.pawn.community.mapper.UserMapper;
import com.pawn.community.pojo.User;
import com.pawn.community.provider.GithubProvider;
import com.pawn.community.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/***
 * description: 登陆
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/6 15:55
 */
@Controller
@Slf4j
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String Client_id;
    @Value("${github,client.secret}")
    private String Client_secret;
    @Value("${github.redirect.uri}")
    private String Redirect_urt;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/callback")
    public String Callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest servletRequest,
                           HttpServletResponse servletResponse) {
        //注入github账户
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setClient_secret(Client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_urt(Redirect_urt);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.githubUser(accessToken);

//        System.out.println(githubUser.getName());
        if (githubUser != null) {
            //获取github账户，并放入数据库
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatar_url());
            //判断数据库是否有该用户的id
            userService.createOrUpdate(user);
            //自定义Cookie
            servletResponse.addCookie(new Cookie("token", token));
            servletRequest.getSession().setAttribute("user", githubUser);
            return "redirect:/";
        } else {
            log.error("Callback get github error,{}", githubUser);
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String Logout(HttpServletRequest request,
                         HttpServletResponse response) {
        //移除
        request.getSession().removeAttribute("user");
        //移除token(覆盖)
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
