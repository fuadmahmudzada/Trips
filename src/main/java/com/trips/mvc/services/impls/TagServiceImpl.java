package com.trips.mvc.services.impls;

import com.trips.mvc.dtos.categorydtos.ArticleCategoryDto;
import com.trips.mvc.dtos.categorydtos.CategoryCreateDto;
import com.trips.mvc.dtos.tagdtos.TagCreateDto;
import com.trips.mvc.dtos.tagdtos.TagDto;
import com.trips.mvc.models.ArticleCategory;
import com.trips.mvc.models.Tag;
import com.trips.mvc.repositories.TagRepository;
import com.trips.mvc.services.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void add(TagCreateDto tagCreateDto) {
        Tag tag = modelMapper.map(tagCreateDto, Tag.class);
        tagRepository.save(tag);
    }
    @Override
    public List<TagDto> getAllTags() {
        List<TagDto> tags = tagRepository.findAll().stream().map(tag ->modelMapper.map(tag, TagDto.class)).toList();
        return tags;
    }
}
