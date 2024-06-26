package com.trips.mvc.controllers.dashboardControllers;

import com.trips.mvc.dtos.categorydtos.ArticleCategoryDto;
import com.trips.mvc.dtos.categorydtos.CategoryCreateDto;
import com.trips.mvc.services.ArticleService;
import com.trips.mvc.services.AuthorService;
import com.trips.mvc.services.CategoryService;
import com.trips.mvc.services.TestimonyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller("DshCategoryController")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TestimonyService testimonyService;

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

}
