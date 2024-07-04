package com.trips.mvc.repositories;

import com.trips.mvc.dtos.SubscribeDto;
import com.trips.mvc.models.SubscribeUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<SubscribeUsers, Long> {
}
