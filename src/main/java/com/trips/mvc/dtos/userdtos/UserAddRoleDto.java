package com.trips.mvc.dtos.userdtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserAddRoleDto {
    private String email;
    private Long roleId;
}