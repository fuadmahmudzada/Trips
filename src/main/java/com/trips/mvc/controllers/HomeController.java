package com.trips.mvc.controllers;

import com.trips.mvc.dtos.articledtos.ArticleHomeDto;
import com.trips.mvc.dtos.authordtos.AuthorDetailDto;
import com.trips.mvc.dtos.testimonydtos.TestimonyDto;
import com.trips.mvc.dtos.tripdtos.TripDto;
import com.trips.mvc.dtos.tripdtos.TripHomeDto;
import com.trips.mvc.services.ArticleService;
import com.trips.mvc.services.TestimonyService;
import com.trips.mvc.services.TripService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {
    @Autowired
    private TestimonyService testimonyService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TripService tripService;
    @GetMapping("/")
    public String home(Model model) {
        List<List<TestimonyDto>> testimonyDtoList = testimonyService.getHomeTestimonials();
List<TripHomeDto> tripHomeDtoList = tripService.getHomeDtos();
        model.addAttribute("testimonials", testimonyDtoList);
        model.addAttribute("trips", tripHomeDtoList );
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
//
//    @GetMapping("/blog")
//    public String blog() {
//        return "blog";
//    }

    @GetMapping("/contact")
    public String contact(Model model) {
        List<List<TestimonyDto>> testimonyDtoList = testimonyService.getHomeTestimonials();
        model.addAttribute("testimonials", testimonyDtoList);
        return "contact";
    }

    @GetMapping("/trips")
    public String trips(Model model, HttpServletRequest request,
                        @RequestParam(name = "page", defaultValue = "1") Optional<Integer> page,
                        @RequestParam(name = "size", defaultValue = "3") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        String currentUrl = request.getRequestURL().toString();
        Integer pageView = page.get();



        Integer sizeView = size.get();

        System.out.println(sizeView);
        System.out.println(pageView);

        model.addAttribute("pageView", pageView);
        model.addAttribute("sizeView", sizeView);
        System.out.println(currentUrl);
        Page<TripDto> bookPage = tripService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("bookPage", bookPage);
//if(currentUrl != null) {
//    System.out.println(currentUrl.charAt(32));
//
//}
        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "trips";
    }

    @GetMapping("/trip-single")
    public String tripSingle() {
        return "trip-single";
    }

    @GetMapping("/blogs/single")
    public String blogSingle() {
        return "singleBlog";
    }

    @GetMapping("/author")
    public String author() {
        return "specificAuthor";
    }

    @GetMapping("/specificCategory/{id}/{seoUrl}")
    public String specificCategory(@PathVariable Long id, Model model){
        List<ArticleHomeDto> articleHomeDtoList = articleService.getCategoryArticles(id);
        model.addAttribute("categoryArticles", articleHomeDtoList);
        return "specificCategory";
    }
}
