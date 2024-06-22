package com.trips.mvc.dtos.articledtos;

import com.trips.mvc.dtos.authordtos.AuthorDto;
import com.trips.mvc.dtos.categorydtos.ArticleCategoryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ArticleDetailDto {
    private Long id;

    private String name;
    //articlein yeridir
    private String content;
    private String photoUrl;
    private Date createdDate;
    private Date updatedDate;

    private ArticleCategoryDto category;
    private AuthorDto author;
}
