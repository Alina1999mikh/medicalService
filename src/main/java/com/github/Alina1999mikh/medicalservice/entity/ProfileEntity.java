package com.github.Alina1999mikh.medicalservice.entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
@Table("PROFILES")
public class ProfileEntity {
    @NonNull BigInteger user_id;
    @NonNull String login;
    @NonNull String password;
    @NonNull String fName;
    @NonNull String sName;
    @NonNull LocalDate date;
    @NonNull Character gender;
}
