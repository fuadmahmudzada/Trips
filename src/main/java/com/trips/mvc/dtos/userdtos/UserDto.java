package com.trips.mvc.dtos.userdtos;

import com.trips.mvc.dtos.roledtos.RoleDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

    @Getter
    @Setter
    public class UserDto {
        private Long id;
        private String email;
        private String firstName;
        private String lastName;
        private List<Long> roleIds;
    }