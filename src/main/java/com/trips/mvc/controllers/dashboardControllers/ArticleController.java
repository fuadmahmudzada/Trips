package com.trips.mvc.controllers.dashboardControllers;

import com.trips.mvc.dtos.articledtos.ArticleCreateDto;
import com.trips.mvc.dtos.articledtos.ArticleDto;
import com.trips.mvc.dtos.articledtos.ArticleUpdateDto;
import com.trips.mvc.dtos.authordtos.AuthorDto;
import com.trips.mvc.dtos.categorydtos.ArticleCategoryDto;
import com.trips.mvc.services.ArticleService;
import com.trips.mvc.services.AuthorService;
import com.trips.mvc.services.CategoryService;
import com.trips.mvc.services.TestimonyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller("DshArticleController")
public class ArticleController {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TestimonyService testimonyService;
    private static String data;
    @GetMapping("/admin/article")
    public String article(Model model) {
        List<ArticleDto> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        var a= 2;
        return "dashboard/article";

    }

    @GetMapping("/admin/article/create")
    public String articleCreate(Model model) {
        List<ArticleCategoryDto> categories = categoryService.getAllCategories();
        List<AuthorDto> authors = authorService.getAuthors();
        model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);
        return "dashboard/articleCreate";

    }

    @PostMapping("/admin/article/create")
    public String articleCreate(@ModelAttribute ArticleCreateDto articleCreateDto) {
        articleService.add(articleCreateDto);
        data = articleCreateDto.getContent();

        return "redirect:/admin/article";
    }


    @GetMapping("/admin/article/update/{id}")
    public String updateArticle(@PathVariable Long id, Model model) {
        ArticleUpdateDto articleUpdateDto = articleService.findUpdateArticle(id);
        List<ArticleCategoryDto> categories = categoryService.getAllCategories();
        List<AuthorDto> authorDtos = authorService.getAuthors();
        model.addAttribute("categories", categories);
        model.addAttribute("article", articleUpdateDto);
        model.addAttribute("authors", authorDtos);
        data = articleUpdateDto.getContent();
        model.addAttribute("data", data);
        return "dashboard/articleUpdate";
    }

    @PostMapping("/admin/article/update")
    public String updateArticle(@ModelAttribute ArticleUpdateDto articleUpdateDto) {

        articleService.updateArticle(articleUpdateDto);

        return "redirect:/admin/article";
    }

    @GetMapping("/admin/article/remove/{id}")
    public String removeArticle(@PathVariable Long id) {
        {
            articleService.removeArticle(id);
            return "redirect:/admin/article";

        }

    }
}