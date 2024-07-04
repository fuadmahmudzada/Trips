package com.trips.mvc.services;

import com.trips.mvc.dtos.articledtos.*;
import com.trips.mvc.dtos.authordtos.AuthorHomeDto;
import com.trips.mvc.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    void add(ArticleCreateDto articleCreateDto);

    List<ArticleDto> getArticles();
    void updateArticle(ArticleUpdateDto articleDto);
    ArticleUpdateDto findUpdateArticle(Long id);
    void removeArticle(Long articleId);
    ArticleDetailDto articleDetail(Long id);
    List<ArticleHomeDto>getHomeArticles();
    AuthorHomeDto articleAuthor(Long id);
    List<ArticleHomeDto> getCategoryArticles(Long id);
    List<ArticleHomeDto> getAuthorArticles(Long id);

    Page<ArticleHomeDto> findPaginated(Pageable pageable);

    ArticleDetailDto getDetail(Long id);
}
