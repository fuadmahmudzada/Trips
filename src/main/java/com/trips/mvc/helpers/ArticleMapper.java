package com.trips.mvc.helpers;

import com.trips.mvc.dtos.articledtos.ArticleDto;
import com.trips.mvc.models.Article;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    ArticleDto toDto(Article article);
}
