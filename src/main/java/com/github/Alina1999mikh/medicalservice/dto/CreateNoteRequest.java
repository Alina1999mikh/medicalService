package com.github.Alina1999mikh.medicalservice.dto;

import javax.validation.constraints.Pattern;
import lombok.Value;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class CreateNoteRequest {
    BigInteger user_id;
    UUID uuid;
    String lab;
    String test;
    LocalDate date;
    String result;
    String referenceRange;
    String unit;
    String comment;
}
