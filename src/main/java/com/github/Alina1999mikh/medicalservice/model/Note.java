package com.github.Alina1999mikh.medicalservice.model;

import com.github.Alina1999mikh.medicalservice.model.fieldDataBase.FullName;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class Note {
    private UUID uuid;
    private String lab;
    private FullName name;
    private String test;
    private Date date;
    private String result;
    private String referenceRange;
    private String unit;
    private String comment;

    public Note(UUID uuid, String lab,FullName name,  String test,Date date, String result, String referenceRange, String unit, String comment) {
        this.uuid = uuid;
        this.lab = lab;
        this.test = test;
        this.name = name;
        this.date = date;
        this.result = result;
        this.referenceRange = referenceRange;
        this.unit = unit;
        this.comment = comment;
    }
}
