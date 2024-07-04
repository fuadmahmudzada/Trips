package com.trips.mvc.controllers;

import com.trips.mvc.dtos.ContactDto;
import com.trips.mvc.dtos.SubscribeDto;
import com.trips.mvc.models.SubscribeUsers;
import com.trips.mvc.repositories.EmailRepository;
import com.trips.mvc.services.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmailRepository emailRepository;

    @PostMapping("home/contact")
    public String sendEmail(@ModelAttribute ContactDto contactDto) {
        emailService.sendMessage(contactDto);
        return "redirect:/contact";
    }
//    @GetMapping("/subscription")
//    public String subscribe(){
//        return "contact";
//    }

    @PostMapping("/home/subscription")
    public String subscribe(@ModelAttribute SubscribeDto subscribeDto, HttpServletRequest request, Model model) {
        emailService.subscribe(subscribeDto);
        model.addAttribute("subscriptionSuccess", true);

        emailRepository.save(modelMapper.map(subscribeDto, SubscribeUsers.class));
        String currentUrl = request.getHeader("Referer");
        if (currentUrl == null || currentUrl.isEmpty()) {
            currentUrl = "/home";
        }

        return "redirect:" + currentUrl;
    }
}
