package com.project.bootboard.controller;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class AdminController {

    @GetMapping("/admin/login-form")
    public String adminLoginForm(@ModelAttribute("check") String check,
                                 @ModelAttribute("error") String error,
                                 Authentication authentication) {
        if (authentication != null) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ADMIN")) {
                    return "/admin/home";
                }
            }
            return "redirect:/board/list";
        }
        return "/admin/login-form";
    }

    @GetMapping("/admin/home")
    public String adminHome() {
        return "/admin/home";
    }

    @GetMapping("/admin/notAuth")
    public String notAuth() {
        return "/etc/auth";
    }

}
