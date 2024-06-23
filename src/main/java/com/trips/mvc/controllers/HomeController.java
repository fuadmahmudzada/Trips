package com.trips.mvc.controllers;

import com.trips.mvc.dtos.articledtos.ArticleHomeDto;
import com.trips.mvc.dtos.authordtos.AuthorDetailDto;
import com.trips.mvc.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
//
//    @GetMapping("/blog")
//    public String blog() {
//        return "blog";
//    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @GetMapping("/trips")
    public String trips() {
        return "trips";
    }

    @GetMapping("/trip-single")
    public String tripSingle() {
        return "trip-single";
    }

    @GetMapping("/blogs/single")
    public String blogSingle() {
        return "singleBlog";
    }

    @GetMapping("/author")
    public String author() {
        return "specificAuthor";
    }

    @GetMapping("/specificCategory/{id}/{seoUrl}")
    public String specificCategory(@PathVariable Long id, Model model){
        List<ArticleHomeDto> articleHomeDtoList = articleService.getCategoryArticles(id);
        model.addAttribute("categoryArticles", articleHomeDtoList);
        return "specificCategory";
    }
}
