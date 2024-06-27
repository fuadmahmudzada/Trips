package com.trips.mvc.dtos.storydtos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoryCreateDto {
    private String header;
    private String content;
    private String imageUrl;
}
