package com.github.Alina1999mikh.medicalservice.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class NoteResponse {
    UUID uuid;
    String lab;
    String firstName;
    String secondName;
    String test;
    LocalDate date;
    String result;
    String referenceRange;
    String unit;
    String comment;
}
