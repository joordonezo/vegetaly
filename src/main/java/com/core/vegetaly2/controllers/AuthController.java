package com.core.vegetaly2.controllers;

import com.core.vegetaly2.dto.AuthResponseDto;
import com.core.vegetaly2.dto.LoginCredentialsDto;
import com.core.vegetaly2.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @PreAuthorize("permitAll")
    @CrossOrigin
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginCredentialsDto loginCredentials){
        return ResponseEntity.ok(authService.login(loginCredentials));
    }

    @PostMapping("/register")
    @PreAuthorize("permitAll")
    @CrossOrigin
    public ResponseEntity<AuthResponseDto> register(@RequestBody LoginCredentialsDto loginCredentials){
        return ResponseEntity.ok(authService.register(loginCredentials));
    }
}
