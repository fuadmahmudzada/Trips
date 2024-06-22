package com.trips.mvc.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDto {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String message;
}
