package com.pawn.community.controller;

import com.pawn.community.mapper.UserMapper;
import com.pawn.community.pojo.Article;
import com.pawn.community.pojo.User;
import com.pawn.community.service.ArticleServiceImpl;
import com.pawn.community.service.TagCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/***
 * description:  发布中心
 * @author:美茹冠玉
 * @Return
 * @param
 * @date 2020/11/6 16:05
 */
@Controller
public class PublishController {
    @Autowired
    ArticleServiceImpl articleService;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model) {
        Article article = articleService.PublishId(id);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("description", article.getDescription());
        model.addAttribute("tag", article.getTag());
        model.addAttribute("id", article.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @GetMapping("/publish")
    public String Publish(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(HttpServletRequest request,
                            @RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "tag", required = false) String tag,
                            @RequestParam(value = "id", required = false) Integer id,
                            Model model
    ) {
        //判断当前登陆状态==>已由拦截器接管
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("tags", TagCache.get());
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "类容不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isBlank(invalid)) {
            model.addAttribute("error", "非法标签" + invalid);
            return "publish";
        }
        //添加
        Article article = new Article(title, description, user.getId(), System.currentTimeMillis(), tag, id);
        //判断
        articleService.createOrUpdate(article);
        return "redirect:/";
    }
}
