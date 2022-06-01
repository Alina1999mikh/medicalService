package com.github.Alina1999mikh.medicalservice.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigInteger;
import java.time.LocalDate;

@Value
@Builder
public class ProfileResponse {
    BigInteger user_id;
    String login;
    String password;
    String fName;
    String sName;
    LocalDate date;
    Character gender;
}