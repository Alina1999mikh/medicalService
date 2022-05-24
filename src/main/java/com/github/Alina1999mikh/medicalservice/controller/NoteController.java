package com.github.Alina1999mikh.medicalservice.controller;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.github.Alina1999mikh.medicalservice.dto.CreateNoteRequest;
import com.github.Alina1999mikh.medicalservice.dto.NoteResponse;
import com.github.Alina1999mikh.medicalservice.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.RemoteEndpoint;
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

    @Operation(summary = "get", security = @SecurityRequirement(name="basicAuth"))
    @GetMapping("/{uuid}")
    ResponseEntity<NoteResponse> findNoteByUuid(@PathVariable UUID uuid) {
        return ResponseEntity.of(noteService.findNoteByUuid(uuid));
    }

    @Operation(summary = "get", security = @SecurityRequirement(name="basicAuth"))
    @GetMapping("/")
    ResponseEntity<List<NoteResponse>> getNotes(Optional<String> test) {
        System.out.println("getmethod");
        if (test.isPresent()) return ResponseEntity.ok(noteService.findNotesByTest(test.get()));
        else return ResponseEntity.ok(noteService.getAllNotes());
    }

    @Operation(summary = "del", security = @SecurityRequirement(name="basicAuth"))
    @DeleteMapping("/" +
            "delete/{uuid}")
    ResponseEntity<?> deleteNoteByUuid(@PathVariable UUID uuid) {
        System.out.println("deletemethod");
        noteService.deleteNoteByUuid(uuid);
        return ResponseEntity.ok().build();
    }
}
