package com.example.jpareldemo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDto {

    private Long id;
    private boolean active;
    private String pictureUrl;

    @JsonBackReference
    private UserDto user;
}
