package com.trips.mvc.controllers.dashboardControllers;

import com.trips.mvc.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "dashboard/forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email, Model model) {
        try {
            passwordResetService.sendPasswordResetEmail(email);
            model.addAttribute("message", "Password reset email sent.");
        } catch (RuntimeException e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "dashboard/forgot-password";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        if (!passwordResetService.validateResetToken(token)) {
            model.addAttribute("error", "Tokein is invalid or expired  ");
            return "error";
        }
        model.addAttribute("token", token);
        return "dashboard/reset-password";
    }

    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam("token") String token,
                                       @RequestParam("password") String password,
                                       Model model) {
        try {
            passwordResetService.resetPassword(token, password);
            model.addAttribute("message", "Password has been reset successfully");
        } catch (RuntimeException e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "/login";
    }
}
