package com.example.springmvc.practise.service;

import com.example.springmvc.practise.core.exceptions.UserAlreadyExistException;
import com.example.springmvc.practise.entity.Users;
import com.example.springmvc.practise.model.UserModel;
import com.example.springmvc.practise.repo.UsersRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {
    @Autowired
    private final UsersRepo userRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // this is  used for encryption


    public List<Users> getAll() {
        return userRepo.findAll();
    }

    @Transactional
    public UserModel createUser(UserModel user) {
        try {
            Users newUser = new Users();
            newUser.setName(user.getName());
//            newUser.setPassword(user.getPassword());
            newUser.setPassword(encoder.encode(user.getPassword()));  // for converting normal password into encryption while saving

            Users savedUser = userRepo.save(newUser);
            return new UserModel(savedUser.getId(), savedUser.getName(), savedUser.getPassword());
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistException("User with name '" + user.getName() + "' already exists");
        }
    }

    public UserModel findByName(String username){
        Users user = userRepo.findByName(username);
        return new UserModel(user.getId(),user.getName(),user.getPassword());
    }



}