package com.trips.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
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
    public String contact() {
        return "contact";
    }

    @GetMapping("/trips")
    public String trips() {
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

}
