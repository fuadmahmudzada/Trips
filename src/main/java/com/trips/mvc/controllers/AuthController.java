package com.trips.mvc.controllers;

import com.trips.mvc.dtos.authdtos.RegisterDto;
import com.trips.mvc.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }


    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("registerDto", new RegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterDto registerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (!registerDto.getPassword().equals(registerDto.getPasswordRepeat())) {
            bindingResult.rejectValue("passwordRepeat", "error.passwordRepeat", "Passwords do not match");
            return "register";
        }

        boolean res = userService.register(registerDto);
        if (!res) {
            model.addAttribute("error", "Registration failed. Email might be already in use.");
            return "register";
        }
        return "redirect:/login?registered";
    }


    @GetMapping("auth/confrim")
    public String confirm(String email, String token)
    {
        boolean res = userService.confirmEmail(email, token);
        return "redirect:/login";
    }
}
