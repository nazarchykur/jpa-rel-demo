package com.example.jpareldemo.repository;

import com.example.jpareldemo.entity.Guild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuildRepository extends JpaRepository<Guild, Long> {
}