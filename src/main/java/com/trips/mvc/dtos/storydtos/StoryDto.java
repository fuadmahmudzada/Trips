package com.trips.mvc.dtos.storydtos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryDto {
    private Long id;
    private String header;
    private String imageUrl;
    private String content;
}
