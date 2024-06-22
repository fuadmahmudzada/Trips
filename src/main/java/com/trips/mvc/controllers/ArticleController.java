package com.trips.mvc.controllers;

import com.trips.mvc.dtos.articledtos.ArticleDetailDto;
import com.trips.mvc.dtos.authordtos.AuthorDetailDto;
import com.trips.mvc.repositories.AuthorRepository;
import com.trips.mvc.services.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
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
    public String singleBlog(@PathVariable Long id, Model model, HttpServletRequest request) {
        System.out.println("Author: " );
        ArticleDetailDto articleDetailDto = articleService.articleDetail(id);
        AuthorDetailDto authorDetailDto = articleService.articleAuthor(id);
        String currentUrl = request.getRequestURI();
//        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("article", articleDetailDto);
        model.addAttribute("author", authorDetailDto);

        return "singleBlog";
    }
//    @GetMapping("/author/{id}/{seourl}")
//    public String singleBlogAuthor(@PathVariable Long id, Model model) {
//
//    }
}
