package com.example.jpareldemo.controller;

import com.example.jpareldemo.dto.AddressDto;
import com.example.jpareldemo.dto.ProfileDto;
import com.example.jpareldemo.dto.UserDto;
import com.example.jpareldemo.entity.Profile;
import com.example.jpareldemo.entity.User;
import com.example.jpareldemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @PostMapping("/{userId}/profile")
    public ResponseEntity<UserDto> addProfile(@PathVariable Long userId, @RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(userService.addProfile(userId, profileDto));
    }

    @PutMapping("/{userId}/address")
    public ResponseEntity<UserDto> setAddress(@PathVariable Long userId, @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(userService.addAddressToUser(userId, addressDto));
    }

    @DeleteMapping("/{userId}/address")
    public ResponseEntity<Void> removeAddress(@PathVariable Long userId) {
        userService.removeAddressFromUser(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.ok().build();
    }
}
