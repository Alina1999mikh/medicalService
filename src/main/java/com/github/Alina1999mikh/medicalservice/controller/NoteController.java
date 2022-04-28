package com.github.Alina1999mikh.medicalservice.controller;

import com.github.Alina1999mikh.medicalservice.dto.CreateNoteRequest;
import com.github.Alina1999mikh.medicalservice.dto.NoteResponse;
import com.github.Alina1999mikh.medicalservice.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    ResponseEntity<?> createNote(@Validated @RequestBody CreateNoteRequest request) {
        noteService.createNote(request);
        return ResponseEntity.created(URI.create("/v1/note/" + request.getUuid())).build();
    }

    @GetMapping("/{uuid}")
    ResponseEntity<NoteResponse> findNoteByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.of(noteService.findNoteByUuid(uuid));
    }

//    @GetMapping("/{test}")
//    ResponseEntity<List<NoteResponse>> findNotesByTest(@PathVariable String test) {
//    }

    @GetMapping("/")
    ResponseEntity<List<NoteResponse>> getNotes(Optional<String> test) {
        if (test.isPresent()) return ResponseEntity.ok(noteService.findNotesByTest(test.get()));
        else return ResponseEntity.ok(noteService.getAllNotes());
    }

    @DeleteMapping("/{uuid}")
    ResponseEntity<?> deleteNoteByUuid(@PathVariable UUID uuid) {
        noteService.deleteNoteByUuid(uuid);
        return ResponseEntity.ok().build();
    }
}
