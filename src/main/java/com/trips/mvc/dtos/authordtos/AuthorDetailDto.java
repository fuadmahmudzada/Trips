package com.trips.mvc.dtos.authordtos;

import com.trips.mvc.dtos.articledtos.ArticleHomeDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDetailDto {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String about;
private ArticleHomeDto articleHomeDto;

}
