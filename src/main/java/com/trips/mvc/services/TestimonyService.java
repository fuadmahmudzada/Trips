package com.trips.mvc.services;

import com.trips.mvc.dtos.articledtos.ArticleUpdateDto;
import com.trips.mvc.dtos.testimonydtos.TestimonyCreateDto;
import com.trips.mvc.dtos.testimonydtos.TestimonyDto;
import com.trips.mvc.dtos.testimonydtos.TestimonyUpdateDto;

import java.util.List;

public interface TestimonyService {
    List<List<TestimonyDto>> getHomeTestimonials();
    List<TestimonyDto> getTestimonials();
   void add(TestimonyCreateDto testimonyCreateDto);
    void updateTestimony(TestimonyUpdateDto testimonyUpdateDto);
    void removeTestimony(Long articleId);
    TestimonyUpdateDto findUpdateTestimony(Long id);
}
