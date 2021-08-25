package com.manukov.controller;

import com.manukov.entity.User;
import com.manukov.service.RoleService;
import com.manukov.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String adminPage(Authentication auth, Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("roles", roleService.getRoles());

        User admin = (User) auth.getPrincipal();
        model.addAttribute("admin", admin);

        model.addAttribute("newUser", new User());
        model.addAttribute("newUserRoles", roleService.getRoles());

        return "admin-page";     //admin Page
    }

    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute("newUser") User newUser, @RequestParam(value = "selectedRoles") String[] selectedRoles) {
        boolean result = userService.addUser(newUser, selectedRoles);
        return "redirect:/admin";
    }


    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "selectedRoles") String[] selectedRoles) {
        boolean result = userService.updateUser(user, selectedRoles);
        return "redirect:/admin";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
