package com.trips.mvc.dtos.authordtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorUpdateDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String about;
}
