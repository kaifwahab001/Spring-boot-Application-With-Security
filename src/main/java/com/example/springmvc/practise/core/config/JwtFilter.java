package com.example.springmvc.practise.core.config;

import com.example.springmvc.practise.entity.Users;
import com.example.springmvc.practise.repo.UsersRepo;
import com.example.springmvc.practise.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtFilter extends OncePerRequestFilter { // this means one request for every filter

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsersRepo usersRepo;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // From client side
        // we have bearer-token dfajfpsajpdfapdjpaj something like this part of request
        String authHeader = request.getHeader("Authorization");  // we want this only
        String token = null;
        String userName = null;

        if(authHeader!=null && authHeader.startsWith("Bearer")){
            token = authHeader.substring(7);  // 7 because Bearer have 6 digit and after six have one space
            userName = jwtService.extractUserName(token);
        }

        if(userName != null && SecurityContextHolder.getContext().getAuthentication()== null){
            Users user = usersRepo.findByName(userName);
            UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(user, null, null);
            SecurityContextHolder.getContext().setAuthentication(authtoken);
        }

        filterChain.doFilter(request,response);

    }
}
