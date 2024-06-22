package com.trips.mvc.dtos.authordtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorHomeDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String seoUrl;

}
