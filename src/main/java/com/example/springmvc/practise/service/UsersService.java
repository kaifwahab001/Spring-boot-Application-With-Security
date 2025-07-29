package com.example.springmvc.practise.service;

import com.example.springmvc.practise.core.exceptions.UserAlreadyExistException;
import com.example.springmvc.practise.entity.Users;
import com.example.springmvc.practise.model.UserModel;
import com.example.springmvc.practise.repo.UsersRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {
    private final UsersRepo userRepo;

    public List<Users> getAll() {
        return userRepo.findAll();
    }

//    @Transactional
//    public UserModel createUser(UserModel user) {
//        Users newuser = new Users();

    /// /        newuser.setId(user.getId());
//        newuser.setName(user.getName());
//        newuser.setPassword(user.getPassword());
//
//        userRepo.save(newuser);
//        System.out.println("user saved");
//
//        return new UserModel(newuser.getId(), newuser.getName(), newuser.getPassword());
//    }

    @Transactional
    public UserModel createUser(UserModel user) {
        try {
            Users newuser = new Users();
            newuser.setName(user.getName());
            newuser.setPassword(user.getPassword());

            Users savedUser = userRepo.save(newuser);
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