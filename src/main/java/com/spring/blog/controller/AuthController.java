package com.spring.blog.controller;

import com.spring.blog.dto.LoginDto;
import com.spring.blog.dto.RegisterDto;
import com.spring.blog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;


    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<String> login (@RequestBody LoginDto loginDto) {
        String response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = {"/register", "signup"})
    public ResponseEntity<String> register (@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return ResponseEntity.ok(response);
    }
}
