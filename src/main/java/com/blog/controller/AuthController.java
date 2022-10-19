package com.blog.controller;


import com.blog.dto.TokenResponseDto;
import com.blog.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")             // localhost:8080/api
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/login")
    public TokenResponseDto token(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        return new TokenResponseDto(authentication.getName(),
                authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" ")),
                token);
    }
}
