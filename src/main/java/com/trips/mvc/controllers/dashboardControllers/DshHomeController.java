package com.trips.mvc.controllers.dashboardControllers;

import com.trips.mvc.dtos.ArticleDto;
import com.trips.mvc.dtos.articledtos.ArticleCreateDto;
import com.trips.mvc.dtos.articledtos.ArticleUpdateDto;
import com.trips.mvc.dtos.authordtos.AuthorCreateDto;
import com.trips.mvc.dtos.authordtos.AuthorDto;
import com.trips.mvc.dtos.authordtos.AuthorUpdateDto;
import com.trips.mvc.dtos.categorydtos.ArticleCategoryDto;
import com.trips.mvc.dtos.categorydtos.CategoryCreateDto;
import com.trips.mvc.services.ArticleService;
import com.trips.mvc.services.AuthorService;
import com.trips.mvc.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DshHomeController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private AuthorService authorService;

    @GetMapping("/admin")
    public String index(){
        return "dashboard/home";
    }

    @GetMapping("/admin/category")
    public String category(Model model){
        List<ArticleCategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "dashboard/category";
    }
    @GetMapping("/admin/category/create")
    public String categoryCreate(){
        return "dashboard/categoryCreate";
    }
    @PostMapping("/admin/category/create")
    public String categoryCreate(@ModelAttribute CategoryCreateDto categoryCreateDto){
        categoryService.add(categoryCreateDto);
        return "redirect:/admin/category";
    }
    @GetMapping("/admin/article")
    public String article(Model model){
        List<ArticleDto> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "dashboard/article";
    }
    @GetMapping("/admin/article/create")
    public String articleCreate(Model model){
        List<ArticleCategoryDto> categories = categoryService.getAllCategories();
        List<AuthorDto> authors = authorService.getAuthors();
        model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);
        return "dashboard/articleCreate";

    }
    @PostMapping("/admin/article/create")
    public String articleCreate(@ModelAttribute ArticleCreateDto articleCreateDto){
        articleService.add(articleCreateDto);
        return "redirect:/admin/article";
    }


    @GetMapping("/admin/article/update/{id}")
    public String updateArticle(@PathVariable Long id, Model model) {
        ArticleUpdateDto articleUpdateDto = articleService.findUpdateArticle(id);
        List<ArticleCategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("article", articleUpdateDto);
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
    @GetMapping("/admin/author")
    public String author(Model model){
        List<AuthorDto> authors = authorService.getAuthors();
        model.addAttribute("authors", authors);
        return "dashboard/author";
    }
    @GetMapping("/admin/author/create")
    public String authorCreate(){
        return "dashboard/authorCreate";
    }

    @PostMapping("/admin/author/create")
    public String authorCreate(@ModelAttribute AuthorCreateDto authorCreateDto){
        authorService.add(authorCreateDto);
        return "redirect:/admin/author";
    }
    @GetMapping("/admin/author/update/{id}")
    public String updateAuthor(@PathVariable Long id, Model model) {
        AuthorUpdateDto authorUpdateDto = authorService.findUpdateAuthor(id);
        model.addAttribute("author", authorUpdateDto);
        return "dashboard/authorUpdate";
    }

    @PostMapping("/admin/author/update")
    public String updateAuthor(@ModelAttribute AuthorUpdateDto authorUpdateDto) {

        authorService.updateAuthor(authorUpdateDto);

        return "redirect:/admin/author";
    }

    @GetMapping("/admin/author/remove/{id}")
    public String removeAuthor(@PathVariable Long id) {
        {
            authorService.removeAuthor(id);
            return "redirect:/admin/author";

        }

    }

}
