package com.example.baitap.Repository;

import com.example.baitap.Entity.UserDemo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDemo, Integer>
{
    UserDemo findByEmail(String email);
}