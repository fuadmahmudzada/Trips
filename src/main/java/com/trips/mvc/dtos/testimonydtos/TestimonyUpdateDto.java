package com.trips.mvc.dtos.testimonydtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestimonyUpdateDto {
    private  Long id;
    private String fullName;
    private String content;
    private String photoUrl;
}
