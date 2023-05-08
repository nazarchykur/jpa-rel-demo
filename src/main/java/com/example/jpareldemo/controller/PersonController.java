package com.example.jpareldemo.controller;

import com.example.jpareldemo.dto.PersonDto;
import com.example.jpareldemo.entity.Note;
import com.example.jpareldemo.entity.Person;
import com.example.jpareldemo.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable Long personId) {
        return ResponseEntity.ok(personService.findById(personId));
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long personId) {
        personService.deleteById(personId);
        log.info("Deleting person with id {}", personId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{personId}/notes")
    public ResponseEntity<Void> addNoteToPerson(@PathVariable Long personId, @RequestBody Note note) {
        personService.addNoteToPerson(personId, note);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{personId}/notes/{noteId}")
    public ResponseEntity<Void> removeNoteFromPerson(@PathVariable Long personId, @PathVariable Long noteId) {
        personService.removeNoteFromPerson(personId, noteId);
        return ResponseEntity.noContent().build();
    }
}
