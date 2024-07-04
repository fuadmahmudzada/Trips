package com.trips.mvc.services;

public interface PasswordResetService {
    void sendPasswordResetEmail(String email);
    String generateResetToken();
    boolean validateResetToken(String token);
    void resetPassword(String token, String newPassword);
}
