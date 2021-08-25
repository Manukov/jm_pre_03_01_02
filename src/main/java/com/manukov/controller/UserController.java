package com.manukov.controller;

import com.manukov.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "")
    public String userPage(Authentication auth, Model model) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);
        return "user-page";
    }
}
