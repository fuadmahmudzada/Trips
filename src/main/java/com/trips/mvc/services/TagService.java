package com.trips.mvc.services;

import com.trips.mvc.dtos.categorydtos.ArticleCategoryDto;
import com.trips.mvc.dtos.categorydtos.CategoryCreateDto;
import com.trips.mvc.dtos.tagdtos.TagCreateDto;
import com.trips.mvc.dtos.tagdtos.TagDto;

import java.util.List;

public interface TagService {
    void add(TagCreateDto tagCreateDto);
    List<TagDto> getAllTags();
}
