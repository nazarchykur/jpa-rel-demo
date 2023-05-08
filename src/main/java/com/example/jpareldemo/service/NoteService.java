package com.example.jpareldemo.service;

import com.example.jpareldemo.entity.Note;
import com.example.jpareldemo.entity.Person;
import com.example.jpareldemo.repository.NoteRepository;
import com.example.jpareldemo.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NoteService {

    private final NoteRepository noteRepository;
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public Note findById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found"));
    }

    public Note saveNoteForPersonAndReturn(Note note, Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));
        note.setPerson(person);

        return noteRepository.save(note);
    }

    public void saveNoteForPerson(Note note, Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));
        note.setPerson(person);

        noteRepository.save(note);
    }

    public void deleteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found"));
        noteRepository.delete(note);
    }

    public List<Note> findAllByPersonId(Long personId) {
        return noteRepository.findAllByPersonId(personId);
    }

    public void saveNoteForPersonUsingProxy(Note note, Long personId) {
        try {
            Person person = personRepository.getReferenceById(personId);
            note.setPerson(person);
            noteRepository.save(note);
        } catch (Exception e) {
            throw new EntityNotFoundException("Failed to save note: person with ID " + personId + " does not exist. Error: " + e.getMessage());
        }
    }
}
