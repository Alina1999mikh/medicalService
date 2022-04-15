package com.github.Alina1999mikh.medicalservice.dto;

import javax.validation.constraints.Pattern;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class CreateNoteRequest {
    UUID uuid;
    String lab;
    String test;
    LocalDate date;
    String result;
    String referenceRange;
    String unit;
    String comment;
}
