package com.github.Alina1999mikh.medicalservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    Long id;
    @NonNull String username;
    @NonNull String firstName;
    @NonNull String secondName;
    @NonNull LocalDate date;
    @NonNull Character gender;
}
