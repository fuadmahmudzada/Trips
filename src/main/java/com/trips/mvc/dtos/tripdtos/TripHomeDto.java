package com.trips.mvc.dtos.tripdtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TripHomeDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Date createdDate;
    private Integer price;
    private String seoUrl;
}
