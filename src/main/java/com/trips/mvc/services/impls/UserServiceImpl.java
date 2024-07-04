package com.trips.mvc.services.impls;

import com.trips.mvc.dtos.authdtos.RegisterDto;
import com.trips.mvc.dtos.userdtos.*;
import com.trips.mvc.models.Role;
import com.trips.mvc.models.UserEntity;
import com.trips.mvc.repositories.RoleRepository;
import com.trips.mvc.repositories.UserRepository;
import com.trips.mvc.services.EmailService;
import com.trips.mvc.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;
//    @Override
//    public UserDto getUpdateUserByid(Long id){
//        UserEntity findUser = userRepository.findById(id).orElseThrow();
//        findUser.getId(user)
//    }
@Override
    @Transactional
    public void updateUserRoles(String email, List<Long> roleIds) {
        UserEntity user = userRepository.findByEmail(email);
        List<Role> newRoles = roleRepository.findAllById(roleIds);
        user.setRoles(new ArrayList<>(newRoles));
        userRepository.save(user);
    }
    @Override
public UserDto getUserWithRoles(Long id) {
    UserEntity user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    UserDto userDto = modelMapper.map(user, UserDto.class);
    userDto.setRoleIds(user.getRoles().stream().map(Role::getId).collect(Collectors.toList()));
    return userDto;
}
    @Override
    public boolean register(RegisterDto register) {

        UserEntity user = userRepository.findByEmail(register.getEmail());
        if (user != null){
            return false;
        }
        if (!register.getPassword().equals(register.getPasswordRepeat())) {
            return false;
        }
        String hashPassword = bCryptPasswordEncoder.encode(register.getPassword());
        String token = bCryptPasswordEncoder.encode(register.getEmail());
        UserEntity newUser = modelMapper.map(register, UserEntity.class);
        newUser.setEmailConfirmed(false);
        newUser.setConfirmationToken(token);
        newUser.setPassword(hashPassword);
        userRepository.save(newUser);
        emailService.sendConfirmationEmail(register.getEmail(),token);
        return true;
    }

    @Override
    public boolean confirmEmail(String email, String token) {

        UserEntity findUser = userRepository.findByEmail(email);
        if (findUser.getConfirmationToken().equals(token) && findUser != null)
        {
            findUser.setEmailConfirmed(true);
            userRepository.saveAndFlush(findUser);
            return true;
        }
        return false;
    }
    @Override
    @Transactional
    public void updateRoles(UserUpdateRoleDto updateRoleDto) {
        UserEntity user = userRepository.findById(updateRoleDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Role> newRoles = roleRepository.findAllById(updateRoleDto.getRoleId());
        user.setRoles(new ArrayList<>(newRoles));

        userRepository.save(user);
    }

    @Override
    public List<UserDashboardListDto> getDashboardUsers() {
        List<UserEntity> findUsers = userRepository.findAll();
        List<UserDashboardListDto> users = findUsers.stream().map(user -> modelMapper.map(user, UserDashboardListDto.class)).collect(Collectors.toList());
        return users;
    }

    @Override
    public UserUpdateDto findUpdateUser(Long id) {
        UserEntity findUser = userRepository.findById(id).orElseThrow();
        UserUpdateDto user = modelMapper.map(findUser, UserUpdateDto.class);
        return user;
    }

    @Override
    public UserUpdateRoleDto findUpdateRoleUser(Long id) {
        UserEntity findUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserUpdateRoleDto userUpdateRoleDto = new UserUpdateRoleDto();
        userUpdateRoleDto.setId(findUser.getId());
        userUpdateRoleDto.setEmail(findUser.getEmail());

        List<Long> roleIds = findUser.getRoles().stream()
                .map(Role::getId)
                .collect(Collectors.toList());
        userUpdateRoleDto.setRoleId(roleIds);

        return userUpdateRoleDto;
    }

    //    @Override
//    public UserDto getUserById(Long id)
//    {
//        UserEntity findUser = userRepository.findById(id).orElseThrow();
//        UserDto user = modelMapper.map(findUser, UserDto.class);
//        return user;
//    }
    @Override
    public void removeRoles(String email, List<Long> roleIds) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        List<Role> rolesToRemove = roleRepository.findAllById(roleIds);
        user.getRoles().removeAll(rolesToRemove);

        userRepository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return user;
    }

//    @Override
//    public void addRoles(UserAddRoleDto userAddRoleDto) {
//        UserEntity user = userRepository.findByEmail(userAddRoleDto.getEmail());
//        if (user == null) {
//            throw new RuntimeException("User not found");
//        }
//
//        List<Role> rolesToAdd = roleRepository.findAllById(userAddRoleDto.getRoleIds());
//
//        // Remove any roles that the user already has
//        rolesToAdd.removeAll(user.getRoles());
//
//        // Add new roles
//        user.getRoles().addAll(rolesToAdd);
//
//        userRepository.save(user);
//    }

//    @Override
//    public UserDto createUser(UserDto userDto) {
//        UserEntity user = modelMapper.map(userDto, UserEntity.class);
//        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
//        UserEntity savedUser = userRepository.save(user);
//        return modelMapper.map(savedUser, UserDto.class);
//    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        UserEntity updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Remove all roles from the user
        user.getRoles().clear();

        // Remove any other associations here if they exist
        // For example: user.getOrders().clear();

        userRepository.delete(user);
    }
    @Override
    public UserDto getUserById(Long id) {
        UserEntity findUser=userRepository.findById(id).orElseThrow();
        UserDto user=modelMapper.map(findUser,UserDto.class);
        return user;
    }
    @Override
    public void addRole(UserAddRoleDto userAddRole) {
        UserEntity findUser = userRepository.findByEmail(userAddRole.getEmail());
        if (findUser == null) {
            throw new RuntimeException("User not found");
        }
        Role roleToAdd = roleRepository.findById(userAddRole.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        if (!findUser.getRoles().contains(roleToAdd)) {
            findUser.getRoles().add(roleToAdd);
            userRepository.save(findUser);
        }
    }

}
