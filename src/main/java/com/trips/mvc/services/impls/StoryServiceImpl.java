package com.trips.mvc.services.impls;

import com.trips.mvc.dtos.bannerdtos.BannerCreateDto;
import com.trips.mvc.dtos.bannerdtos.BannerDto;
import com.trips.mvc.dtos.bannerdtos.BannerUpdateDto;
import com.trips.mvc.dtos.storydtos.StoryCreateDto;
import com.trips.mvc.dtos.storydtos.StoryDto;
import com.trips.mvc.dtos.storydtos.StoryUpdateDto;
import com.trips.mvc.models.Banner;
import com.trips.mvc.models.Story;
import com.trips.mvc.repositories.BannerRepository;
import com.trips.mvc.repositories.StoryRepository;
import com.trips.mvc.services.StoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoryServiceImpl implements StoryService {
    @Autowired
    private ModelMapper modelMapper;
  @Autowired
  private StoryRepository storyRepository;
    @Override
    public void add(StoryCreateDto storyCreateDto) {
        try {
            Story story = new Story();


//            article.setAuthor(articleCreateDto.getAuthor());
            story.setContent(storyCreateDto.getContent());
            story.setHeader(storyCreateDto.getHeader());
            story.setImageUrl(storyCreateDto.getImageUrl());




            storyRepository.save(story);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public StoryDto getStory() {

        StoryDto storyDto = storyRepository.findAll()
                .stream().map(story -> modelMapper.map(story, StoryDto.class))
                .findFirst().orElseThrow();
        return storyDto;
    }

    @Override
    public void updateStory(StoryUpdateDto storyUpdateDto) {
        Story findStory = storyRepository.findById(storyUpdateDto.getId()).orElseThrow();

        findStory.setId(storyUpdateDto.getId());
        findStory.setHeader(storyUpdateDto.getHeader());
//        findArticle.setAuthor(articleUpdateDto.getAuthor());
        findStory.setContent(storyUpdateDto.getContent());


        findStory.setImageUrl(storyUpdateDto.getImageUrl());


        storyRepository.saveAndFlush(findStory);
    }

    @Override
    public StoryUpdateDto findUpdateStory(Long id) {
        Story story = storyRepository.findById(id).orElseThrow();
        StoryUpdateDto storyUpdateDto = modelMapper.map(story, StoryUpdateDto.class);
        return storyUpdateDto;
    }

}
