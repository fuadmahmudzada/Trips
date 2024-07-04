package com.trips.mvc.services.impls;

import com.trips.mvc.models.OneTimeToken;
import com.trips.mvc.repositories.OneTimeTokenRepository;
import com.trips.mvc.services.OneTimeTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class OneTimeTokenServiceImpl implements OneTimeTokenService {
    @Autowired
    private OneTimeTokenRepository tokenRepository;

    public String generateToken() {
        String token = UUID.randomUUID().toString();
        OneTimeToken oneTimeToken = new OneTimeToken();
        oneTimeToken.setToken(token);
        oneTimeToken.setExpirationTime(LocalDateTime.now().plusHours(24)); // Token valid for 24 hours
        oneTimeToken.setUsed(false);
        tokenRepository.save(oneTimeToken);
        return token;
    }

    public boolean validateToken(String token) {
        Optional<OneTimeToken> optionalToken = tokenRepository.findByToken(token);
        if (optionalToken.isPresent()) {
            OneTimeToken oneTimeToken = optionalToken.get();
            if (!oneTimeToken.isUsed() && oneTimeToken.getExpirationTime().isAfter(LocalDateTime.now())) {
                oneTimeToken.setUsed(true);
                tokenRepository.save(oneTimeToken);
                return true;
            }
        }
        return false;
    }

    public boolean validateToken2(String token) {
        Optional<OneTimeToken> optionalToken = tokenRepository.findByToken(token);
        if (optionalToken.isPresent()) {
            OneTimeToken oneTimeToken = optionalToken.get();
            if (!oneTimeToken.isUsed() && oneTimeToken.getExpirationTime().isAfter(LocalDateTime.now())) {
                oneTimeToken.setUsed(true);
                tokenRepository.save(oneTimeToken);
                return true;
            }
        }
        return false;
    }
}
