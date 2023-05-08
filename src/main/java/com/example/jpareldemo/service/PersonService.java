package com.example.jpareldemo.service;

import com.example.jpareldemo.dto.PersonDto;
import com.example.jpareldemo.entity.Note;
import com.example.jpareldemo.entity.Person;
import com.example.jpareldemo.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public PersonDto findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        person.getNotes().stream()
                .forEach(System.out::println);

        return modelMapper.map(person, PersonDto.class);
    }

    // 1 way
//    @Transactional
//    public void deleteById(Long id) {
//        personRepository.findById(id)
//                .ifPresentOrElse(
//                        personRepository::delete,
//                        () -> {
//                            throw new EntityNotFoundException("Person not found");
//                        }
//                );
//    }

    // 2 way
//    @Transactional
    public void deleteById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        personRepository.delete(person);
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }

//    @Transactional
    public void addNoteToPerson(Long personId, Note note) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        person.addNote(note);
    }

//    @Transactional
    public void removeNoteFromPerson(Long personId, Long noteId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));

        Note note = person.getNotes().stream()
                .filter(n -> n.getId().equals(noteId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Note not found"));

        person.removeNote(note);
    }
}
