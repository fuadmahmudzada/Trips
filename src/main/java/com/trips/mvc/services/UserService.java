package com.trips.mvc.services;

import com.trips.mvc.dtos.authdtos.RegisterDto;
import com.trips.mvc.dtos.userdtos.*;
import com.trips.mvc.models.UserEntity;

import java.util.List;

public interface UserService {
    boolean register(RegisterDto register);
    boolean confirmEmail(String email, String token);
    List<UserDashboardListDto> getDashboardUsers();
    UserUpdateDto findUpdateUser(Long id);
    UserUpdateRoleDto findUpdateRoleUser(Long id);
//    void addRole(UserAddRoleDto userAddRole);
  //  UserDto createUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
//    void addRoles(UserAddRoleDto userAddRoleDto);
    void removeRoles(String email, List<Long> roleIds);
    UserDto getUserWithRoles(Long id);
     UserDto getUserById(Long id);
     void addRole(UserAddRoleDto userAddRole);
    UserEntity findByEmail(String email);
    void updateRoles(UserUpdateRoleDto updateRoleDto);

    void updateUserRoles(String email, List<Long> roleIds);
}
