package com.example.jpareldemo.repository;

import com.example.jpareldemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}