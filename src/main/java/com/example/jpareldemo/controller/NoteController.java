package com.example.jpareldemo.controller;

import com.example.jpareldemo.entity.Note;
import com.example.jpareldemo.service.NoteService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> findAllByPersonId(@PathVariable Long personId) {
        return ResponseEntity.ok(noteService.findAllByPersonId(personId));
    }

//    @PostMapping
//    public ResponseEntity<Note> save(@RequestBody Note note, @PathVariable Long personId) {
//        log.info("Saving note {} for person {}", note, personId);
//        return ResponseEntity.ok(noteService.saveNoteForPerson(note, personId));
//    }
    @GetMapping("/{noteId}")
    public ResponseEntity<Note> findById(@PathVariable Long noteId) {
        return ResponseEntity.ok(noteService.findById(noteId));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long noteId) {
        log.info("Deleting note {}", noteId);
        noteService.deleteById(noteId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Note note, @RequestParam Long personId) {
        log.info("Saving note {} for person {}", note, personId);
        noteService.saveNoteForPerson(note, personId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/with-uri")
    public ResponseEntity<Void> saveWithUri(@RequestBody Note note, @RequestParam Long personId) {
        log.info("Saving note {} for person {}", note, personId);
        Note savedNote = noteService.saveNoteForPersonAndReturn(note, personId);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedNote.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/returning-note")
    public ResponseEntity<Note> saveAndReturnNote(@RequestBody Note note, @RequestParam Long personId) {
        log.info("Saving note {} for person {}", note, personId);
        return ResponseEntity.ok(noteService.saveNoteForPersonAndReturn(note, personId));
    }

    @PostMapping("/using-proxy")
    public ResponseEntity<Void> saveAndReturnNoteUsingProxy(@RequestBody Note note, @RequestParam Long personId) {
        log.info("Saving note {} for person {}", note, personId);
        noteService.saveNoteForPersonUsingProxy(note, personId);
        return ResponseEntity.noContent().build();
    }


}
