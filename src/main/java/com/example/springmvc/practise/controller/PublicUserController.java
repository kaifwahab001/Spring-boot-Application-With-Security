package com.example.springmvc.practise.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicUserController {

    @GetMapping("/user")
    public String user(){
        return "User is public";
    }

}
