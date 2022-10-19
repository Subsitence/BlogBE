package com.blog.controller;


import com.blog.dto.StatusResponseDto;
import com.blog.dto.UserRegistrationDto;
import com.blog.model.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")     // localhost:8080/api/registration
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<StatusResponseDto> saveUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        User user = userService.saveUser(userRegistrationDto);
        if (user == null) {
            return new ResponseEntity<>(new StatusResponseDto("Can't create account!"), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(new StatusResponseDto("Registration success"), HttpStatus.CREATED);
    }
}
