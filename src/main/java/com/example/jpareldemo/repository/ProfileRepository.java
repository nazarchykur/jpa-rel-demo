package com.example.jpareldemo.repository;

import com.example.jpareldemo.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}