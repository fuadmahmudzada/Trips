package com.trips.mvc.dtos.tripdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripCreateDto {
    private String name;

    private String description;
    private String content;
    private String imageUrl;
    private String backImageUrl;
    private Integer price;

}
