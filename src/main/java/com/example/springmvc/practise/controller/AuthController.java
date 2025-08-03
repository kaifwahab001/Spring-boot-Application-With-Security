package com.example.springmvc.practise.controller;

import com.example.springmvc.practise.entity.Users;
import com.example.springmvc.practise.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final  AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return authService.verify(user);
    }
}
