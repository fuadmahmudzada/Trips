package com.trips.mvc.dtos.articledtos;


import com.trips.mvc.dtos.authordtos.AuthorDto;

import com.trips.mvc.dtos.categorydtos.CategoryHomeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleHomeDto {
    private Long id;
    private String name;
    private String description;
    private String photoUrl;
    private Date createdDate;
    private String seoUrl;
private CategoryHomeDto categoryHomeDto;
    private AuthorDto authorDto;
}
