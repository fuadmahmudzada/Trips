package com.trips.mvc.controllers;

import com.trips.mvc.repositories.TripRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class TripController {
    private final TripRepository tripRepository;

    public TripController(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @GetMapping("/home/trips/tripDetail/{seoUrl}/{id}")
    public String tripDetail(Model model, @PathVariable Long id){
        model.addAttribute("trip", tripRepository.findById(id).orElseThrow());
        return "trip-single";
    }
}
