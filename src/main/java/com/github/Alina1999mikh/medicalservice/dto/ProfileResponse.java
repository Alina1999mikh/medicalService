package com.github.Alina1999mikh.medicalservice.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class ProfileResponse {
    String username;
    String firstName;
    String secondName;
    LocalDate date;
    Character gender;
}