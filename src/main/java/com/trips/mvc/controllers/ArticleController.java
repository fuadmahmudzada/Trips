package com.trips.mvc.controllers;

import com.trips.mvc.dtos.articledtos.ArticleDetailDto;
import com.trips.mvc.dtos.articledtos.ArticleHomeDto;
import com.trips.mvc.dtos.articledtos.ArticleRelatedDto;
import com.trips.mvc.dtos.authordtos.AuthorDetailDto;
import com.trips.mvc.dtos.authordtos.AuthorHomeDto;
import com.trips.mvc.dtos.commentdtos.CommentCreateDto;
import com.trips.mvc.dtos.commentdtos.CommentDto;
import com.trips.mvc.repositories.ArticleRepository;
import com.trips.mvc.repositories.AuthorRepository;
import com.trips.mvc.services.ArticleService;
import com.trips.mvc.services.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ArticleController {
        @Autowired
        private ArticleService articleService;
    @Autowired
    private AuthorRepository authorService;
    @Autowired
    private ArticleRepository articleRepository;
@Autowired
private CommentService commentService;
    @GetMapping("/home/blogs/{id}/{seoUrl}")
    public String singleBlog(@PathVariable Long id, Model model, HttpServletRequest request) {
        System.out.println("Author: " );
        ArticleDetailDto articleDetailDto = articleService.articleDetail(id);
        AuthorHomeDto authorHomeDto = articleService.articleAuthor(id);
        List<ArticleHomeDto> articleHomeDtoList = articleService.getCategoryArticles(articleRepository.findById(id).orElseThrow().getArticleCategory().getId());
        System.out.println("category name:" );
        System.out.println("category id:" + articleDetailDto.getCategory().getId());
        System.out.println("category seoUrl:" + articleDetailDto.getCategory().getSeoUrl());

//        String currentUrl = request.getRequestURI();
//        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute("categoryArticles", articleHomeDtoList);
        model.addAttribute("article", articleDetailDto);
        model.addAttribute("author", authorHomeDto);
        ArticleDetailDto articleDetail = articleService.getDetail(id);

        List<CommentDto> commentDto = commentService.getCommentsByArticleId(id);
//        List<ArticleRelatedDto> articleRelated = articleService.getRelatedArticles(articleDetail.getCategory().getId());

        model.addAttribute("comments",commentDto);
        return "singleBlog";
    }

    @PostMapping("/home/blogs/{id}/{seoUrl}")
    public String addComment(CommentCreateDto commentCreate, Principal principal, @PathVariable Long id){
        String username = principal.getName();
        commentCreate.setArticleId(id);

        commentService.addComment(commentCreate,username);
        return "redirect:/home/blogs/"+commentCreate.getArticleId() + "/" + articleRepository.findById(commentCreate.getArticleId()).get().getSeoUrl();
    }
//    @GetMapping("/author/{id}/{seourl}")
//    public String singleBlogAuthor(@PathVariable Long id, Model model) {
//
//    }
}
