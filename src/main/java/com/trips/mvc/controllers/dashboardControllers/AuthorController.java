package com.trips.mvc.controllers.dashboardControllers;

import com.trips.mvc.dtos.authordtos.AuthorCreateDto;
import com.trips.mvc.dtos.authordtos.AuthorDto;
import com.trips.mvc.dtos.authordtos.AuthorUpdateDto;
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
@Controller("DshAuthorController")
public class AuthorController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TestimonyService testimonyService;

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
