package com.trips.mvc.controllers;

import com.trips.mvc.dtos.articledtos.ArticleDto;
import com.trips.mvc.dtos.articledtos.ArticleHomeDto;
import com.trips.mvc.repositories.ArticleRepository;
import com.trips.mvc.services.ArticleService;
import com.trips.mvc.services.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BlogHomeController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService; // 123
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/home/blog")
    public String index(  Model model, HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Optional<Integer> page,
                          @RequestParam(name = "size", defaultValue = "3") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        String currentUrl = request.getRequestURL().toString();
//        StringBuilder queryString = new StringBuilder();

           Integer pageView = page.get();



           Integer sizeView = size.get();

        System.out.println(sizeView);
        System.out.println(pageView);

        model.addAttribute("pageView", pageView);
        model.addAttribute("sizeView", sizeView);
        System.out.println(currentUrl);
        Page<ArticleHomeDto> bookPage = articleService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

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

        return "blog";
    }




}
//    @GetMapping("/getAll/{offset}")
//    public Iterable<Product> getAllProducts(@RequestParam Integer pageSize, @PathVariable("offset") Integer offset){
//        return productService.getAllProducts(pageSize,offset);
//    }
//    @GetMapping("/blogs")
//    public Page<ArticleHomeDto> findAll(@RequestParam Optional <String> name,
//                                        @RequestParam Optional<Integer> page){
//        return articleRepository.findByName(name.orElse('_'), new PageRequest(page.orElse(0),5));
//    }

