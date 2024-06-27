package com.trips.mvc.services;

import com.trips.mvc.dtos.bannerdtos.BannerCreateDto;
import com.trips.mvc.dtos.bannerdtos.BannerDto;
import com.trips.mvc.dtos.bannerdtos.BannerUpdateDto;
import com.trips.mvc.dtos.storydtos.StoryCreateDto;
import com.trips.mvc.dtos.storydtos.StoryDto;
import com.trips.mvc.dtos.storydtos.StoryUpdateDto;

public interface StoryService {

    void add(StoryCreateDto storyCreateDto);

    StoryDto getStory();
    void updateStory(StoryUpdateDto storyDto);
    StoryUpdateDto findUpdateStory(Long id);
}

