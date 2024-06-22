package com.trips.mvc.services;

import com.trips.mvc.dtos.ArticleDto;
import com.trips.mvc.dtos.articledtos.ArticleCreateDto;
import com.trips.mvc.dtos.articledtos.ArticleDetailDto;
import com.trips.mvc.dtos.articledtos.ArticleHomeDto;
import com.trips.mvc.dtos.articledtos.ArticleUpdateDto;
import com.trips.mvc.dtos.authordtos.AuthorDetailDto;

import java.util.List;

public interface ArticleService {
    void add(ArticleCreateDto articleCreateDto);

    List<ArticleDto> getArticles();
    void updateArticle(ArticleUpdateDto articleDto);
    ArticleUpdateDto findUpdateArticle(Long id);
    void removeArticle(Long articleId);
    ArticleDetailDto articleDetail(Long id);
    List<ArticleHomeDto> getHomeArticles();
    AuthorDetailDto articleAuthor(Long id);
}
