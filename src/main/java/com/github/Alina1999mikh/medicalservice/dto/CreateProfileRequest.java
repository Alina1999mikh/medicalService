package com.github.Alina1999mikh.medicalservice.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class CreateProfileRequest {
    String username;
    String firstName;
    String secondName;
    LocalDate date;
    Character gender;
}
