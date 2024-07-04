package com.trips.mvc.services;

public interface OneTimeTokenService {
    public String generateToken();
    public boolean validateToken(String token);
    boolean validateToken2(String token);
}
