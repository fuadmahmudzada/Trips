package com.trips.mvc.controllers.dashboardControllers;

import com.trips.mvc.dtos.bannerdtos.BannerCreateDto;
import com.trips.mvc.dtos.bannerdtos.BannerDto;
import com.trips.mvc.dtos.bannerdtos.BannerUpdateDto;
import com.trips.mvc.dtos.storydtos.StoryCreateDto;
import com.trips.mvc.dtos.storydtos.StoryDto;
import com.trips.mvc.dtos.storydtos.StoryUpdateDto;
import com.trips.mvc.services.BannerService;
import com.trips.mvc.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StoryController {
    @Autowired
    private StoryService storyService;
    private static String data;
    @GetMapping("/admin/story")
    public String index(Model model) {
        StoryDto story = storyService.getStory();
        model.addAttribute("story", story);
        var a= 2;
        return "dashboard/story";

    }

    @GetMapping("/admin/story/create")
    public String articleCreate() {

        return "dashboard/storyCreate";

    }

    @PostMapping("/admin/story/create")
    public String articleCreate(@ModelAttribute StoryCreateDto storyCreateDto) {
        storyService.add(storyCreateDto);
        data = storyCreateDto.getContent();

        return "redirect:/admin/story";
    }


    @GetMapping("/admin/story/update/{id}")
    public String updateArticle(@PathVariable Long id, Model model) {
        StoryUpdateDto storyUpdateDto = storyService.findUpdateStory(id);


        model.addAttribute("story", storyUpdateDto);

        data = storyUpdateDto.getContent();
        model.addAttribute("data", data);
        return "dashboard/storyUpdate";
    }

    @PostMapping("/admin/story/update")
    public String updateArticle(@ModelAttribute StoryUpdateDto storyUpdateDto) {

        storyService.updateStory(storyUpdateDto);

        return "redirect:/admin/story";
    }

}
