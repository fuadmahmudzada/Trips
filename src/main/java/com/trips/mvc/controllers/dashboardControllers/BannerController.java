package com.trips.mvc.controllers.dashboardControllers;

import com.trips.mvc.dtos.articledtos.ArticleCreateDto;
import com.trips.mvc.dtos.articledtos.ArticleDto;
import com.trips.mvc.dtos.articledtos.ArticleUpdateDto;
import com.trips.mvc.dtos.authordtos.AuthorDto;
import com.trips.mvc.dtos.bannerdtos.BannerCreateDto;
import com.trips.mvc.dtos.bannerdtos.BannerDto;
import com.trips.mvc.dtos.bannerdtos.BannerUpdateDto;
import com.trips.mvc.dtos.categorydtos.ArticleCategoryDto;
import com.trips.mvc.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller("dshBannerController")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    private static String data;
    @GetMapping("/admin/banner")
    public String index(Model model) {
        BannerDto banner = bannerService.getBanner();
        model.addAttribute("banner", banner);
        var a= 2;
        return "dashboard/banner";

    }

    @GetMapping("/admin/banner/create")
    public String articleCreate( ) {

        return "dashboard/bannerCreate";

    }

    @PostMapping("/admin/banner/create")
    public String articleCreate(@ModelAttribute BannerCreateDto bannerCreateDto) {
        bannerService.add(bannerCreateDto);
        data = bannerCreateDto.getContent();

        return "redirect:/admin/banner";
    }


    @GetMapping("/admin/banner/update/{id}")
    public String updateArticle(@PathVariable Long id, Model model) {
        BannerUpdateDto bannerUpdateDto = bannerService.findUpdateBanner(id);


        model.addAttribute("banner", bannerUpdateDto);

        data = bannerUpdateDto.getContent();
        model.addAttribute("data", data);
        return "dashboard/bannerUpdate";
    }

    @PostMapping("/admin/banner/update")
    public String updateArticle(@ModelAttribute BannerUpdateDto bannerUpdateDto) {

        bannerService.updateBanner(bannerUpdateDto);

        return "redirect:/admin/banner";
    }

}
