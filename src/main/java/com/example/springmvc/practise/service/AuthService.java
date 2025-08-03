package com.example.springmvc.practise.service;

import com.example.springmvc.practise.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

        public String verify(Users user) {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())
                    );

            if(authentication.isAuthenticated()){
                return jwtService.genrerateToken(user.getName());
            }
            return "fail";
        }
}
