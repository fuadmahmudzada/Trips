package com.trips.mvc.controllers.dashboardControllers;

import com.trips.mvc.dtos.roledtos.RoleDto;
import com.trips.mvc.dtos.userdtos.*;
import com.trips.mvc.models.UserEntity;
import com.trips.mvc.repositories.UserRepository;
import com.trips.mvc.services.RoleService;
import com.trips.mvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller

public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/admin/users")
    public String getUsers(Model model)
    {
        List<UserDashboardListDto> userList=userService.getDashboardUsers();
        model.addAttribute("myusers",userList);
        return "/dashboard/auth/userlist";
    }


    @GetMapping("/admin/users/role/{id}")
    public String addRole(@PathVariable Long id, Model model) {
        UserDto user = userService.getUserWithRoles(id); // Make sure this method returns roles
        List<RoleDto> allRoles = roleService.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", allRoles);
        return "/dashboard/auth/userrole";
    }
    @PostMapping("/admin/users/addroles")
    public String updateRoles(@RequestParam String email, @RequestParam(required = false) List<Long> roleIds, RedirectAttributes redirectAttributes) {
        try {
            userService.updateUserRoles(email, roleIds != null ? roleIds : new ArrayList<>());
            redirectAttributes.addFlashAttribute("successMessage", "Roles updated successfully");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/users";
    }



    @PostMapping("admin/users/removeroles")
    public String removeRoles(@RequestParam String email, @RequestParam List<Long> roleIds) {
        userService.removeRoles(email, roleIds);
        return "redirect:/admin/users";
    }


    @GetMapping("/admin/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        UserUpdateDto user = userService.findUpdateUser(id);
        model.addAttribute("user", user);
        return "dashboard/auth/user-form";
    }

    @PostMapping("/admin/users/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute UserDto userDto) {
        userService.updateUser(id, userDto);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/users";
    }

}
