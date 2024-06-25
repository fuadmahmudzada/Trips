package com.trips.mvc.repositories;

import com.trips.mvc.models.Testimony;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestimonyRepository extends JpaRepository<Testimony, Long> {
}
