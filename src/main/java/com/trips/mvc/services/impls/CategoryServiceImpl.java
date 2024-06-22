package com.trips.mvc.services.impls;


import com.trips.mvc.dtos.categorydtos.ArticleCategoryDto;
import com.trips.mvc.dtos.categorydtos.CategoryCreateDto;
import com.trips.mvc.models.ArticleCategory;
import com.trips.mvc.repositories.CategoryRepository;
import com.trips.mvc.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void add(CategoryCreateDto categoryCreateDto) {
        ArticleCategory category = modelMapper.map(categoryCreateDto, ArticleCategory.class);
        categoryRepository.save(category);
    }
    @Override
    public List<ArticleCategoryDto> getAllCategories() {
        List<ArticleCategoryDto> categories = categoryRepository.findAll().stream().map(category ->modelMapper.map(category, ArticleCategoryDto.class)).toList();
        return categories;
    }
}
