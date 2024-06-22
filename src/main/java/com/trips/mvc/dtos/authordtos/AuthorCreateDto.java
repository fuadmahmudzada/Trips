package com.trips.mvc.dtos.authordtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorCreateDto {
    private String name;
    private String description;
    private String imageUrl;
    private String about;
}
