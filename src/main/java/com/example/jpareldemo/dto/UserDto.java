package com.example.jpareldemo.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto {

    private Long id;
    private String name;

    @JsonManagedReference
    private ProfileDto profile;

    @JsonManagedReference
    private AddressDto address;
}
