package com.trips.mvc.controllers.dashboardControllers;

import com.trips.mvc.dtos.categorydtos.ArticleCategoryDto;
import com.trips.mvc.dtos.categorydtos.CategoryCreateDto;
import com.trips.mvc.dtos.tagdtos.TagCreateDto;
import com.trips.mvc.dtos.tagdtos.TagDto;
import com.trips.mvc.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TagController {
    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TestimonyService testimonyService;

    @GetMapping("/admin/tag")
    public String tag(Model model){
        List<TagDto> tags = tagService.getAllTags();
        model.addAttribute("tags", tags);
        return "dashboard/tag";
    }
    @GetMapping("/admin/tag/create")
    public String tagCreate(){
        return "dashboard/tagCreate";
    }
    @PostMapping("/admin/tag/create")
    public String categoryCreate(@ModelAttribute TagCreateDto tagCreateDto){
        tagService.add(tagCreateDto);
        return "redirect:/admin/tag";
    }
}
