package com.github.Alina1999mikh.medicalservice.entity;

import com.github.Alina1999mikh.medicalservice.model.fieldDataBase.FullName;
import com.github.Alina1999mikh.medicalservice.model.fieldDataBase.*;
import com.github.Alina1999mikh.medicalservice.model.fieldDataBase.result.Result;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Value
@Table("NOTES")
public class NoteEntity {
    @Id
    Long id;
    UUID uuid;
    Lab lab;
    FullName name;
    TestName test;
    Date date;
    Result result;
    ReferenceRange referenceRange;
    Unit unit;
    Comment comment;
}
