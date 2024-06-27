package com.trips.mvc.services;

import com.trips.mvc.dtos.articledtos.*;
import com.trips.mvc.dtos.tripdtos.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TripService {
    void add(TripCreateDto tripCreateDto);

    List<TripDto> getTrips();

    void updateTrip(TripUpdateDto tripUpdateDto);

    TripUpdateDto findUpdateTrip(Long id);

    void removeTrip(Long articleId);

    TripDetailDto tripDetail(Long id);

    List<TripHomeDto> getHomeDtos();
    Page<TripDto> findPaginated(Pageable pageable);
}