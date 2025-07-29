package com.example.springmvc.practise.repo;

import com.example.springmvc.practise.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
    Users findByName(String name);
}
