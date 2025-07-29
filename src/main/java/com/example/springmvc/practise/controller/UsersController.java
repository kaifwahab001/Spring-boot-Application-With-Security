package com.example.springmvc.practise.controller;

import com.example.springmvc.practise.entity.Users;
import com.example.springmvc.practise.model.UserModel;
import com.example.springmvc.practise.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    @Autowired
    private final UsersService usersService;


    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> getUsers = usersService.getAll();
        return ResponseEntity.ok(getUsers);
    }


    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userBody){
        UserModel user = usersService.createUser(userBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
