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
public class GuildDto {
    private Long id;
    private String name;

//    @JsonManagedReference
    @JsonIgnoreProperties("guilds")
    private List<EmployeeDto> employees = new ArrayList<>();
}
