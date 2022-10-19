package com.blog.controller;

import com.blog.model.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")        // localhost:8080/api/user
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
