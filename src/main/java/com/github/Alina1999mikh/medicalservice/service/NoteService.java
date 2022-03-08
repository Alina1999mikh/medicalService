package com.github.Alina1999mikh.medicalservice.service;

import com.github.Alina1999mikh.medicalservice.dto.CreateNoteRequest;
import com.github.Alina1999mikh.medicalservice.dto.NoteResponse;
import com.github.Alina1999mikh.medicalservice.entity.NoteEntity;
import com.github.Alina1999mikh.medicalservice.model.ParsedFullName;
import com.github.Alina1999mikh.medicalservice.repository.NoteRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public void createNote(CreateNoteRequest request) {
        ParsedFullName parsedFullName = parseFullName(request.getFullName());
        noteRepository.save(NoteEntity.builder()
                .uuid(request.getUuid())
                .lab(request.getLab())
                .firstName(parsedFullName.getFirstName())
                .secondName(parsedFullName.getSecondName())
                .test(request.getTest())
                .date(request.getDate())
                .result(request.getResult())
                .referenceRange(request.getReferenceRange())
                .unit(request.getUnit())
                .comment(request.getComment())
                .build()
        );
    }

    public Optional<NoteResponse> findNoteByUuid(UUID uuid) {
        return noteRepository.findByUuid(uuid)
                .map(it -> NoteResponse.builder()
                        .uuid(it.getUuid())
                        .lab(it.getLab())
                        .firstName(it.getFirstName())
                        .secondName(it.getSecondName())
                        .test(it.getTest())
                        .date(it.getDate())
                        .result(it.getResult())
                        .referenceRange(it.getReferenceRange())
                        .unit(it.getUnit())
                        .comment(it.getComment())
                        .build());
    }

    ParsedFullName parseFullName(String fullNameString) {
        String[] parsedString = fullNameString.split(" ");
        if (parsedString.length != 2) {
            throw new ValidationException("Incorrect name format: " + fullNameString);
        }
        return new ParsedFullName(parsedString[0], parsedString[1]);
    }

    public void deleteNoteByUuid(UUID uuid) {
        noteRepository.findByUuid(uuid)
                .ifPresent(noteRepository::delete);
    }
}
