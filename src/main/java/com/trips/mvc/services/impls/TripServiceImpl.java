package com.trips.mvc.services.impls;

import com.trips.mvc.dtos.articledtos.*;
import com.trips.mvc.dtos.authordtos.AuthorDto;
import com.trips.mvc.dtos.authordtos.AuthorHomeDto;
import com.trips.mvc.dtos.categorydtos.CategoryHomeDto;
import com.trips.mvc.dtos.tripdtos.*;
import com.trips.mvc.helpers.SeoHelper;
import com.trips.mvc.models.Article;
import com.trips.mvc.models.ArticleCategory;
import com.trips.mvc.models.Author;
import com.trips.mvc.models.Trip;
import com.trips.mvc.repositories.ArticleRepository;
import com.trips.mvc.repositories.AuthorRepository;
import com.trips.mvc.repositories.CategoryRepository;
import com.trips.mvc.repositories.TripRepository;
import com.trips.mvc.services.TripService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Service
public class TripServiceImpl implements TripService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TripRepository tripRepository;


    @Override
    public void add(TripCreateDto tripCreateDto) {
        try {
            Trip trip = new Trip();
            trip.setUpdatedDate(new Date());
            trip.setCreatedDate(new Date());
//            article.setAuthor(articleCreateDto.getAuthor());
            trip.setContent(tripCreateDto.getContent());
            trip.setPrice(tripCreateDto.getPrice());
            trip.setName(tripCreateDto.getName());
            trip.setImageUrl(tripCreateDto.getImageUrl());
            trip.setBackImageUrl(tripCreateDto.getBackImageUrl());
            SeoHelper seoHelper = new SeoHelper();
            trip.setDeleted(false);
            trip.setSeoUrl(seoHelper.seoUrlHelper(tripCreateDto.getName()));
            trip.setDescription(tripCreateDto.getDescription());

            tripRepository.save(trip);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateTrip(TripUpdateDto tripUpdateDto) {
        Trip findTrip = tripRepository.findById(tripUpdateDto.getId()).orElseThrow();

        findTrip.setId(tripUpdateDto.getId());
        findTrip.setName(tripUpdateDto.getName());
//        findArticle.setAuthor(articleUpdateDto.getAuthor());
        findTrip.setContent(tripUpdateDto.getContent());
findTrip.setPrice(tripUpdateDto.getPrice());
        SeoHelper seoHelper = new SeoHelper();
        findTrip.setSeoUrl(seoHelper.seoUrlHelper(tripUpdateDto.getName()));

        findTrip.setDescription(tripUpdateDto.getDescription());
        findTrip.setUpdatedDate(new Date());
        findTrip.setImageUrl(tripUpdateDto.getImageUrl());
        findTrip.setBackImageUrl(tripUpdateDto.getBackImageUrl());

//        findArticle.setAuthor(author);
        tripRepository.saveAndFlush(findTrip);
    }

    @Override
    public TripUpdateDto findUpdateTrip(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow();
        TripUpdateDto tripUpdateDto = modelMapper.map(trip, TripUpdateDto.class);
        return tripUpdateDto;
    }

    @Override
    public List<TripDto> getTrips() {
        List<TripDto> tripDtoList = tripRepository.findAll().stream()
                .filter(x -> x.isDeleted() == false)
                .map(trip -> modelMapper.map(trip, TripDto.class))
                .collect(Collectors.toList());
        return tripDtoList;
    }

    @Override
    public void removeTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow();
        trip.setDeleted(true);
        tripRepository.save(trip);
    }

    @Override
    public TripDetailDto tripDetail(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow();
        TripDetailDto tripDetailDto = modelMapper.map(trip, TripDetailDto.class);
        return tripDetailDto;
    }

    @Override
    public List<TripHomeDto> getHomeDtos() {
//        List<TripHomeDto> tripHomeDtoList = tripRepository.findAll()
//                .stream().filter(x->!x.isDeleted() ).map(trip-> modelMapper.map(trip, TripHomeDto.class)).collect(Collectors.toList());
        List<TripHomeDto> tripHomeDtoList1 = new ArrayList<>();
        tripHomeDtoList1.add(modelMapper.map(tripRepository.findByName("Tripnew") , TripHomeDto.class));
        tripHomeDtoList1.add( modelMapper.map(tripRepository.findByName("Trips2") , TripHomeDto.class));

        return tripHomeDtoList1;

    }
}
