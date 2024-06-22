package com.trips.mvc.controllers;

import com.trips.mvc.dtos.articledtos.ArticleHomeDto;
import com.trips.mvc.repositories.ArticleRepository;
import com.trips.mvc.services.ArticleService;
import com.trips.mvc.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogHomeController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService; // 123
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/blog")
    public String index(Model model)
    {
        System.out.println("Hello");
        List<ArticleHomeDto> articles =  articleService.getHomeArticles();
        model.addAttribute("articles", articles);
        return "blog";
    }
//    @GetMapping("/blogs")
//    public Page<ArticleHomeDto> findAll(@RequestParam Optional <String> name,
//                                        @RequestParam Optional<Integer> page){
//        return articleRepository.findByName(name.orElse('_'), new PageRequest(page.orElse(0),5));
//    }
}
