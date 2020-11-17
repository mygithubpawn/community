package com.pawn.community.controller;

import com.pawn.community.service.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    ArticleServiceImpl articleService;

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }


}
