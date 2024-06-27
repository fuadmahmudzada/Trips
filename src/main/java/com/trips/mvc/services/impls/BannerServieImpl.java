package com.trips.mvc.services.impls;

import com.trips.mvc.dtos.articledtos.ArticleCreateDto;
import com.trips.mvc.dtos.articledtos.ArticleDto;
import com.trips.mvc.dtos.articledtos.ArticleUpdateDto;
import com.trips.mvc.dtos.bannerdtos.BannerCreateDto;
import com.trips.mvc.dtos.bannerdtos.BannerDto;
import com.trips.mvc.dtos.bannerdtos.BannerUpdateDto;
import com.trips.mvc.helpers.SeoHelper;
import com.trips.mvc.models.Article;
import com.trips.mvc.models.ArticleCategory;
import com.trips.mvc.models.Author;
import com.trips.mvc.models.Banner;
import com.trips.mvc.repositories.BannerRepository;
import com.trips.mvc.services.BannerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannerServieImpl implements BannerService {
    @Autowired
    private  BannerRepository bannerRepository;
@Autowired
private ModelMapper modelMapper;
    public BannerServieImpl(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @Override
    public void add(BannerCreateDto bannerCreateDto) {
        try {
            Banner banner = new Banner();


//            article.setAuthor(articleCreateDto.getAuthor());
            banner.setContent(bannerCreateDto.getContent());
            banner.setHeader(bannerCreateDto.getHeader());
            banner.setImage(bannerCreateDto.getImage());





            bannerRepository.save(banner);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public BannerDto getBanner() {

        BannerDto bannerDto = bannerRepository.findAll()
                .stream().map(banner -> modelMapper.map(banner, BannerDto.class))
                .findFirst().orElseThrow();
        return bannerDto;
    }

    @Override
    public void updateBanner(BannerUpdateDto bannerDto) {
        Banner findBanner = bannerRepository.findById(bannerDto.getId()).orElseThrow();

        findBanner.setId(bannerDto.getId());
        findBanner.setHeader(bannerDto.getHeader());
//        findArticle.setAuthor(articleUpdateDto.getAuthor());
        findBanner.setContent(bannerDto.getContent());


        findBanner.setImage(bannerDto.getImage());


        bannerRepository.saveAndFlush(findBanner);
    }

    @Override
    public BannerUpdateDto findUpdateBanner(Long id) {
        Banner banner = bannerRepository.findById(id).orElseThrow();
        BannerUpdateDto bannerUpdateDto = modelMapper.map(banner, BannerUpdateDto.class);
        return bannerUpdateDto;
    }


}
