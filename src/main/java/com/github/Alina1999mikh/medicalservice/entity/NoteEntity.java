package com.github.Alina1999mikh.medicalservice.entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
@Table("NOTES")
public class NoteEntity {
    @Id
    Long id;
    @NonNull UUID uuid;
    @NonNull String lab;
    @NonNull String firstName;
    @NonNull String secondName;
    @NonNull String test;
    @NonNull LocalDate date;
    @NonNull String result;
    @NonNull String referenceRange;
    @NonNull String unit;
    String comment;
}
