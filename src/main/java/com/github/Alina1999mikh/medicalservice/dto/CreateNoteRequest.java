package com.github.Alina1999mikh.medicalservice.dto;

import com.github.Alina1999mikh.medicalservice.model.fieldDataBase.*;
import com.github.Alina1999mikh.medicalservice.model.fieldDataBase.result.Result;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

@Value
public class CreateNoteRequest {
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
