package com.trips.mvc.dtos.articledtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleCreateDto {
    private String name;

    private String description;
    private String content;
    private String photoUrl;

    private Long categoryId;
    private Long authorId;
}
