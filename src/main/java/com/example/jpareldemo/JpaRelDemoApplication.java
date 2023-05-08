package com.example.jpareldemo;

import com.example.jpareldemo.entity.Employee;
import com.example.jpareldemo.entity.Guild;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Consumer;

@SpringBootApplication
public class JpaRelDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaRelDemoApplication.class, args);
    }
}
