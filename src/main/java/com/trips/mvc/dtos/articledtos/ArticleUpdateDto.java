package com.trips.mvc.dtos.articledtos;

import com.trips.mvc.dtos.authordtos.AuthorDto;
import com.trips.mvc.models.Author;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleUpdateDto {
    private Long id;
    private String name;
//    private String author;
    private String description;
    private String content;
    private String photoUrl;

    private Long categoryId;
    private AuthorDto author;
}
