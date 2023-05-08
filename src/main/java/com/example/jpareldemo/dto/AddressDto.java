package com.example.jpareldemo.dto;

import com.example.jpareldemo.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "user")
public class AddressDto {

    private Long id;
    private String street;
    private String city;

    @JsonBackReference
    private UserDto user;
}
