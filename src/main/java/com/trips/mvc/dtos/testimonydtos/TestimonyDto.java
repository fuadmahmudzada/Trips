package com.trips.mvc.dtos.testimonydtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TestimonyDto {
    private Long id;
    private String fullName;
    private String content;
    private String photoUrl;
    private boolean isDeleted;
    private Date createdDate;
    private Date updatedDate;
}
