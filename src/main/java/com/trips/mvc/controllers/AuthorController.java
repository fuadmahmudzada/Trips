package com.trips.mvc.controllers;

import com.trips.mvc.dtos.articledtos.ArticleDetailDto;
import com.trips.mvc.dtos.articledtos.ArticleHomeDto;
import com.trips.mvc.dtos.authordtos.AuthorDetailDto;
import com.trips.mvc.services.ArticleService;
import com.trips.mvc.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/home/author/{id}/{seoUrl}")
    public String spesificAuthor(@PathVariable Long id, Model model) {

        List<ArticleHomeDto> authorArticleDtoList = articleService.getAuthorArticles(id);
        model.addAttribute("authorArticles", authorArticleDtoList);

        return "specificAuthor";
    }
}
