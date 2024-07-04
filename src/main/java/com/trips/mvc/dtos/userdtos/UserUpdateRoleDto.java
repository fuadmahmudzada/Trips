package com.trips.mvc.dtos.userdtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserUpdateRoleDto {
    private Long id;
    private String email;
    private List<Long> roleId;
}
