package com.trips.mvc.controllers;

import com.trips.mvc.dtos.ContactDto;
import com.trips.mvc.dtos.SubscribeDto;
import com.trips.mvc.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/contact")
    public String sendEmail(@ModelAttribute ContactDto contactDto) {
        emailService.sendMessage(contactDto);
        return "redirect:/contact";
    }
//    @GetMapping("/subscription")
//    public String subscribe(){
//        return "contact";
//    }

    @PostMapping("/subscription")
    public String subscribe(@ModelAttribute SubscribeDto subscribeDto) {
        emailService.subscribe(subscribeDto);
        return "redirect:/contact";
    }
}
