package com.trips.mvc.dtos.userdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
}
