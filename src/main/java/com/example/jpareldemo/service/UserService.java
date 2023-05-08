package com.example.jpareldemo.service;

import com.example.jpareldemo.dto.AddressDto;
import com.example.jpareldemo.dto.ProfileDto;
import com.example.jpareldemo.dto.UserDto;
import com.example.jpareldemo.entity.Address;
import com.example.jpareldemo.entity.Profile;
import com.example.jpareldemo.entity.User;
import com.example.jpareldemo.repository.ProfileRepository;
import com.example.jpareldemo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDto findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto addProfile(Long userId, ProfileDto profileDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Profile profile = modelMapper.map(profileDto, Profile.class);
        user.setProfile(profile);

        return modelMapper.map(user, UserDto.class);
    }

    public UserDto save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserDto.class);
    }

    public void deleteById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userRepository.delete(user);
    }

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    public UserDto addAddressToUser(Long userId, AddressDto addressDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Address address = modelMapper.map(addressDto, Address.class);
        user.setAddress(address);

        return modelMapper.map(user, UserDto.class);
    }

    public void removeAddressFromUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.removeAddress(user.getAddress());
    }
}
