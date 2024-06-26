package com.trips.mvc.controllers.dashboardControllers;

import com.trips.mvc.dtos.testimonydtos.TestimonyCreateDto;
import com.trips.mvc.dtos.testimonydtos.TestimonyDto;
import com.trips.mvc.dtos.testimonydtos.TestimonyUpdateDto;
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
@Controller("DshTestimonyController")
public class TestimonyController {

    @Autowired
    private TestimonyService testimonyService;


    @GetMapping("/admin/testimonials")
    public String testimonial(Model model){
        List<TestimonyDto> testimonyDtoList = testimonyService.getTestimonials();
        model.addAttribute("testimonials", testimonyDtoList);
        return "dashboard/testimony";
    }
    @GetMapping("/admin/testimonials/create")
    public String testimonyCreate(){
        return "dashboard/testimonyCreate";
    }
    @PostMapping("/admin/testimonials/create")
    public String testimonyCreate(@ModelAttribute TestimonyCreateDto testimonyCreateDto){
        testimonyService.add(testimonyCreateDto);
        return "redirect:/admin/testimonials";
    }

    @GetMapping("/admin/testimonials/update/{id}")
    public String updateTestimony(@PathVariable Long id, Model model) {
        TestimonyUpdateDto testimonyUpdateDto = testimonyService.findUpdateTestimony(id);

        model.addAttribute("testimony", testimonyUpdateDto);
        return "dashboard/testimonyUpdate";
    }
    @PostMapping("/admin/testimonials/update")
    public String updateTestimony(@ModelAttribute TestimonyUpdateDto testimonyUpdateDto) {

        testimonyService.updateTestimony(testimonyUpdateDto);

        return "redirect:/admin/testimonials";
    }

    @GetMapping("/admin/testimonials/remove/{id}")
    public String removeTestimony(@PathVariable Long id) {
        {
            testimonyService.removeTestimony(id);
            return "redirect:/admin/testimonials";

        }

    }
}
