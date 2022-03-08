package com.github.Alina1999mikh.medicalservice.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class CreateNoteRequest {
    UUID uuid;
    String lab;
    @Pattern(regexp = "[a-zA-Zа-яА-Я]+ [a-zA-Zа-яА-Я]+")
    String fullName;
    String test;
    LocalDate date;
    String result;
    String referenceRange;
    String unit;
    String comment;
}
