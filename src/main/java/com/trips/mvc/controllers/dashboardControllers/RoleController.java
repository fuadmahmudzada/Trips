package com.trips.mvc.controllers.dashboardControllers;

import com.trips.mvc.dtos.categorydtos.CategoryCreateDto;
import com.trips.mvc.dtos.roledtos.RoleDto;
import com.trips.mvc.dtos.userdtos.UserDto;
import com.trips.mvc.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("/admin/roles")
    public String role(Model model){
        List<RoleDto> roleDtoList =roleService.getRoles();
        model.addAttribute("roles", roleDtoList);
        return "dashboard/auth/role";
    }
    @GetMapping("/admin/roles/create")
    public String createUserForm() {
        return "dashboard/auth/roleCreate";
    }

    @PostMapping("/admin/roles/create")
    public String roleCreate(@ModelAttribute RoleDto roleDto){
        roleService.createRole(roleDto);
        return "redirect:/admin/roles";
    }
}
