package com.trips.mvc.services;

import com.trips.mvc.dtos.categorydtos.ArticleCategoryDto;
import com.trips.mvc.dtos.categorydtos.CategoryCreateDto;

import java.util.List;

public interface CategoryService {
    void add(CategoryCreateDto categoryCreateDto);
    List<ArticleCategoryDto> getAllCategories();
}
