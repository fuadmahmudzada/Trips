package com.trips.mvc.services.impls;

import com.trips.mvc.dtos.articledtos.ArticleUpdateDto;
import com.trips.mvc.dtos.testimonydtos.TestimonyCreateDto;
import com.trips.mvc.dtos.testimonydtos.TestimonyDto;
import com.trips.mvc.dtos.testimonydtos.TestimonyUpdateDto;
import com.trips.mvc.helpers.SeoHelper;
import com.trips.mvc.models.Article;
import com.trips.mvc.models.ArticleCategory;
import com.trips.mvc.models.Author;
import com.trips.mvc.models.Testimony;
import com.trips.mvc.repositories.TestimonyRepository;
import com.trips.mvc.services.TestimonyService;
import groovyjarjarantlr4.v4.gui.TestRig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestimonyServiceImpl implements TestimonyService {

    @Autowired
    private ModelMapper modelMapper;
    private final TestimonyRepository testimonyRepository;

    public TestimonyServiceImpl(TestimonyRepository testimonyRepository) {
        this.testimonyRepository = testimonyRepository;
    }

    @Override
    public List<List<TestimonyDto>> getHomeTestimonials() {
//        List<List<String>> listOfLists = new ArrayList<List<String>>();
        int[][] oddNumbers = { {1, 3, 5, 7}, {9, 11, 13, 15}, {17, 19, 21} };

//        List<List<TestimonyDto>> testimonyDtoList1 = testimonyRepository.findAll().stream()
//                .filter(x -> x.isDeleted() == false)
//                .map(testimony -> modelMapper.map(testimony, TestimonyDto.class))
//                .collect()
        List<TestimonyDto> testimonyDtoList = testimonyRepository.findAll().stream()
                .filter(x -> x.isDeleted() == false)
                .map(testimony -> modelMapper.map(testimony, TestimonyDto.class))
                .collect(Collectors.toList());
//        for(int i = 0;i<testimonyDtoList.size();i++){
//            for (int j=0;j<1; j++){
//                listOfLists[i][j]=;
//            }
//        }

        List<List<TestimonyDto>> listOfLists = new ArrayList<List<TestimonyDto>>();
        int numberOfArrays;
        int count=-1;
        if(testimonyDtoList.size()%2==0){
            numberOfArrays=testimonyDtoList.size()/2;
        }
        else {
            numberOfArrays=(testimonyDtoList.size()-1)/2+1;
        }

        for(int i=0; i<numberOfArrays; i++){
            String name = i + ". list";

            listOfLists.add( new ArrayList<TestimonyDto>());
            for(int j=0;j<2;j++){

                count+=1;
                if(count<=testimonyDtoList.size()-1) {
                    listOfLists.get(i).add(j, testimonyDtoList.get(count));
                    System.out.println(listOfLists.get(i).get(j).getFullName());
                }

            }


        }

//for(List<TestimonyDto>testimony: listOfLists ){
//    System.out.println(testimony.get(0).getFullName());
//    System.out.println(testimony.get(1).getFullName());
//}
        System.out.println();
        return listOfLists;

    }

    @Override
    public List<TestimonyDto> getTestimonials() {
        List<TestimonyDto> testimonyDtoList = testimonyRepository.findAll().stream()
                .filter(x -> x.isDeleted() == false)
                .map(testimony -> modelMapper.map(testimony, TestimonyDto.class))
                .collect(Collectors.toList());
        return  testimonyDtoList;
    }

    @Override
    public void add(TestimonyCreateDto testimonyCreateDto) {
        try {
            Testimony testimony = new Testimony();
            testimony.setUpdatedDate(new Date());
            testimony.setCreatedDate(new Date());
//            article.setAuthor(articleCreateDto.getAuthor());
            testimony.setContent(testimonyCreateDto.getContent());
            testimony.setFullName(testimonyCreateDto.getFullName());
            testimony.setPhotoUrl(testimonyCreateDto.getPhotoUrl());

            testimony.setDeleted(false);


            testimonyRepository.save(testimony);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateTestimony(TestimonyUpdateDto testimonyUpdateDto) {
        Testimony findTestimony = testimonyRepository.findById(testimonyUpdateDto.getId()).orElseThrow();


        findTestimony.setId(testimonyUpdateDto.getId());
        findTestimony.setFullName(testimonyUpdateDto.getFullName());
//        findArticle.setAuthor(articleUpdateDto.getAuthor());
        findTestimony.setContent(testimonyUpdateDto.getContent());



        findTestimony.setUpdatedDate(new Date());


//        findArticle.setAuthor(author);
        testimonyRepository.saveAndFlush(findTestimony);
    }
    public void removeTestimony(Long articleId) {
        Testimony testimony = testimonyRepository.findById(articleId).orElseThrow();
        testimony.setDeleted(true);
        testimonyRepository.save(testimony);
    }

    @Override
    public TestimonyUpdateDto findUpdateTestimony(Long id) {

            Testimony testimony = testimonyRepository.findById(id).orElseThrow();
            TestimonyUpdateDto testimonyUpdateDto = modelMapper.map(testimony, TestimonyUpdateDto.class);
            return testimonyUpdateDto;

    }
}
