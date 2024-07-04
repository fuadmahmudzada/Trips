package com.trips.mvc.repositories;

import com.trips.mvc.models.OneTimeToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OneTimeTokenRepository extends JpaRepository<OneTimeToken, Long> {
    Optional<OneTimeToken> findByToken(String token);
}