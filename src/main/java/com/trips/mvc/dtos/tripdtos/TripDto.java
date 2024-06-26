package com.trips.mvc.dtos.tripdtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class TripDto {
    private Long id;

    private String name;

    private String description;
    private String content;
    private String imageUrl;
    private String bakcImageUrl;
    private String seoUrl;
    private Integer price;
    private Date createdDate;
    private Date updatedDate;
}
