package com.trips.mvc.repositories;

import com.trips.mvc.controllers.dashboardControllers.TripController;
import com.trips.mvc.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
    Trip findByName(String name);
}
