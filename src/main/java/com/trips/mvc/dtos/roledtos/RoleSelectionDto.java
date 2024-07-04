package com.trips.mvc.dtos.roledtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleSelectionDto {
    private Long id;
    private String name;
    private boolean selected;
}