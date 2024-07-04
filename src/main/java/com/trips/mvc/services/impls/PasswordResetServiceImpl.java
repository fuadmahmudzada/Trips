package com.trips.mvc.services.impls;

import com.trips.mvc.models.UserEntity;
import com.trips.mvc.repositories.UserRepository;
import com.trips.mvc.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Autowired
    private JavaMailSender passwordResetMailSender;

    @Autowired
    private UserRepository userRepository;

    public void sendPasswordResetEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        String token = generateResetToken();
        user.setResetToken(token);
        user.setResetTokenExpiryDate(LocalDateTime.now().plusHours(24));
        userRepository.save(user);

        String resetLink = "http://localhost:5050/reset-password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("walter.olson32@ethereal.email");
        message.setTo(user.getEmail());
        message.setSubject("Password Reset Request");
        message.setText(resetLink);

        passwordResetMailSender.send(message);
    }

    public String generateResetToken() {
        return UUID.randomUUID().toString();
    }

    public boolean validateResetToken(String token) {
        UserEntity user = userRepository.findByResetToken(token);
        return user != null && user.getResetTokenExpiryDate().isAfter(LocalDateTime.now());
    }

    public void resetPassword(String token, String newPassword) {
        UserEntity user = userRepository.findByResetToken(token);
        if (user == null || user.getResetTokenExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invalid or expired reset token");
        }

        user.setPassword(newPassword);
        user.setResetToken(null);
        user.setResetTokenExpiryDate(null);
        userRepository.save(user);
    }
}