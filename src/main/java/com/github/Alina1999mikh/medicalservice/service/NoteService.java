package com.github.Alina1999mikh.medicalservice.service;

import com.github.Alina1999mikh.medicalservice.dto.CreateNoteRequest;
import com.github.Alina1999mikh.medicalservice.dto.NoteResponse;
import com.github.Alina1999mikh.medicalservice.entity.NoteEntity;
import com.github.Alina1999mikh.medicalservice.repository.NoteRepository;
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
        noteRepository.save(new NoteEntity(null, request.getUuid(), request.getLab(),request.getName(),
                request.getTest(),request.getDate(),request.getResult(),request.getReferenceRange(),
                request.getUnit(),request.getComment()));
    }

    public Optional<NoteResponse> findNoteByUuid(UUID uuid) {
        return noteRepository.findByUuid(uuid)
                .map(it -> new NoteResponse(it.getUuid(), it.getLab(),it.getName(),
                        it.getTest(),it.getDate(),it.getResult(),it.getReferenceRange(),
                        it.getUnit(),it.getComment()));
    }

    public void deleteNoteByUuid(UUID uuid) {
        noteRepository.findByUuid(uuid)
                .ifPresent(noteRepository::delete);
    }
}
