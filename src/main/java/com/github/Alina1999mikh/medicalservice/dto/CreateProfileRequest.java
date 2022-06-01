package com.github.Alina1999mikh.medicalservice.dto;

import lombok.Value;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class CreateProfileRequest {
    BigInteger userId;
    String login;
    String password;
    String fName;
    String sName;
    LocalDate date;
    Character gender;
}
