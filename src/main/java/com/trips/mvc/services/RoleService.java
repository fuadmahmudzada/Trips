package com.trips.mvc.services;

import com.trips.mvc.dtos.roledtos.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> getRoles();
    RoleDto createRole(RoleDto roleDto);
    RoleDto updateRole(Long id, RoleDto roleDto);
    void deleteRole(Long id);
    RoleDto getRoleById(Long id);
}
