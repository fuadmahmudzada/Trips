package com.trips.mvc.controllers;

import com.trips.mvc.dtos.articledtos.ArticleDetailDto;
import com.trips.mvc.dtos.authordtos.AuthorDetailDto;
import com.trips.mvc.repositories.AuthorRepository;
import com.trips.mvc.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleController {
        @Autowired
        private ArticleService articleService;
    @Autowired
    private AuthorRepository authorService;

    @GetMapping("/blog/{id}/{seoUrl}")
    public String singleBlog(@PathVariable Long id, Model model) {
        ArticleDetailDto articleDetailDto = articleService.articleDetail(id);
        AuthorDetailDto authorDetailDto = articleService.articleAuthor(id);
        model.addAttribute("article", articleDetailDto);
        model.addAttribute("author", authorDetailDto);
        System.out.println("Author: " + articleDetailDto.getAuthor());
        return "singleBlog";
    }
//    @GetMapping("/author/{id}/{seourl}")
//    public String singleBlogAuthor(@PathVariable Long id, Model model) {
//
//    }
}
