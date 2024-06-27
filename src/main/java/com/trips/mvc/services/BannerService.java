package com.trips.mvc.services;

import com.trips.mvc.dtos.articledtos.ArticleCreateDto;
import com.trips.mvc.dtos.articledtos.ArticleDto;
import com.trips.mvc.dtos.articledtos.ArticleUpdateDto;
import com.trips.mvc.dtos.bannerdtos.BannerCreateDto;
import com.trips.mvc.dtos.bannerdtos.BannerDto;
import com.trips.mvc.dtos.bannerdtos.BannerUpdateDto;

import java.util.List;

public interface BannerService {

    void add(BannerCreateDto bannerCreateDto);

    BannerDto getBanner();
    void updateBanner(BannerUpdateDto bannerDto);
    BannerUpdateDto findUpdateBanner(Long id);
}
