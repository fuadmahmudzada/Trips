package com.trips.mvc.dtos.tripdtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class TripDetailDto {
    private Long id;

    private String name;
    //articlein yeridir
    private String content;
    private String imageUrl;
    private String backImageUrl;
    private Date createdDate;
    private Date updatedDate;
    private Integer price;
}
