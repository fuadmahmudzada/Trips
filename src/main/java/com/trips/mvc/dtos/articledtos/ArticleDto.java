package com.trips.mvc.dtos.articledtos;

import com.trips.mvc.dtos.authordtos.AuthorDto;
import com.trips.mvc.dtos.categorydtos.ArticleCategoryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ArticleDto {
    private Long id;

    private String name;

    private String description;
    private String content;
    private String photoUrl;
    private String seoUrl;
    private Date createdDate;
    private Date updatedDate;

    private ArticleCategoryDto category;
    private AuthorDto author;
}
