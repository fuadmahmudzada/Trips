package com.trips.mvc.controllers.dashboardControllers;

import com.trips.mvc.dtos.articledtos.ArticleCreateDto;
import com.trips.mvc.dtos.articledtos.ArticleDto;
import com.trips.mvc.dtos.articledtos.ArticleUpdateDto;
import com.trips.mvc.dtos.authordtos.AuthorDto;
import com.trips.mvc.dtos.categorydtos.ArticleCategoryDto;
import com.trips.mvc.dtos.tripdtos.TripCreateDto;
import com.trips.mvc.dtos.tripdtos.TripDto;
import com.trips.mvc.dtos.tripdtos.TripUpdateDto;
import com.trips.mvc.repositories.TripRepository;
import com.trips.mvc.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller("DshTripController")
public class TripController {
    @Autowired
    TripService tripService;
    @Autowired
    private TripRepository tripRepository;
    public static String data;
    @GetMapping("/admin/trip")
    public String article(Model model) {
        List<TripDto> trips = tripService.getTrips();
        model.addAttribute("trips", trips);
        return "dashboard/trip";
    }

    @GetMapping("/admin/trip/create")
    public String articleCreate() {

        return "dashboard/tripCreate";

    }

    @PostMapping("/admin/trip/create")
    public String articleCreate(@ModelAttribute TripCreateDto tripCreateDto) {
        tripService.add(tripCreateDto);
       data = tripCreateDto.getContent();
        return "redirect:/admin/trip";
    }


    @GetMapping("/admin/trip/update/{id}")
    public String updateArticle(@PathVariable Long id, Model model) {
        TripUpdateDto tripUpdateDto = tripService.findUpdateTrip(id);
        data = tripUpdateDto.getContent();
        model.addAttribute("datas", data);

        model.addAttribute("trip", tripUpdateDto);
        return "dashboard/tripUpdate";
    }

    @PostMapping("/admin/trip/update")
    public String updateArticle(@ModelAttribute TripUpdateDto tripUpdateDto) {
        System.out.println(data);
        tripService.updateTrip(tripUpdateDto);
        data = tripUpdateDto.getContent();
        return "redirect:/admin/trip";
    }

    @GetMapping("/admin/trip/remove/{id}")
    public String removeArticle(@PathVariable Long id) {
        {
            tripService.removeTrip(id);
            return "redirect:/admin/trip";

        }

    }


}
