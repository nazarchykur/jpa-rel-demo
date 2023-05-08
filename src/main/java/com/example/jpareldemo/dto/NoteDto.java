package com.example.jpareldemo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoteDto {
    private Long id;
    private String title;

    @JsonBackReference
    private PersonDto person;
}
