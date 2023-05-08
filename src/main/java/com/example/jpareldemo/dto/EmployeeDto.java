package com.example.jpareldemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;

    //    @JsonBackReference
    @JsonIgnoreProperties("employees")
    private List<GuildDto> guilds = new ArrayList<>();
}
