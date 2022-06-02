package com.github.Alina1999mikh.medicalservice.entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Value
@Builder
@Table("PROFILES")
public class ProfileEntity {
    @Id
    @NonNull String username;
    @NonNull String fName;
    @NonNull String sName;
    @NonNull LocalDate date;
    @NonNull Character gender;
}
