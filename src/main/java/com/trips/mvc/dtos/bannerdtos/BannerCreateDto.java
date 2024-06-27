package com.trips.mvc.dtos.bannerdtos;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BannerCreateDto {

    private String header;
    private String content;
    private String image;
}
