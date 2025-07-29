package com.example.springmvc.practise.service;

import com.example.springmvc.practise.model.UserModel;
import com.example.springmvc.practise.model.UserPrinciple;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private final UsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = usersService.findByName(username);

        if(user==null){
            System.out.println("user not found");
            throw  new UsernameNotFoundException("user not found with "+username);
        }

        return new UserPrinciple(user);
    }
}
