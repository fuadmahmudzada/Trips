package com.trips.mvc.dtos.categorydtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryHomeDto {
    private Long id;
    private String name;
    private String seoUrl;
}
