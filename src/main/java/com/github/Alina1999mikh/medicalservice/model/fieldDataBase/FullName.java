package com.github.Alina1999mikh.medicalservice.model.fieldDataBase;

import lombok.Getter;

import java.util.Locale;
import java.util.regex.Pattern;

@Getter
public class FullName {
    String firstName;
    String secondName;
    private static final String SEPARATOR=" ";
    private static final String regexName = ("[a-zA-Z]+ | [а-яА-Я]+");

    public FullName(String firstName, String secondName) {
        if (isCorrectName(firstName) && isCorrectName(secondName))
            this.firstName = firstUpperCase(firstName);
        this.secondName = firstUpperCase(secondName);
    }

    public FullName(String fullName) throws Exception {
        String[] names=getFirstSecondName(fullName);
        if (isCorrectName(firstName) && isCorrectName(secondName))
            this.firstName = firstUpperCase(firstName);
        this.secondName = firstUpperCase(secondName);
    }


    private String[] getFirstSecondName(String fullName) throws Exception {
        String[] names=fullName.split(SEPARATOR);
        if(names.length!=2) throw new Exception();
        else return names;
    }

    private String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase(Locale.ROOT);
    }

    boolean isCorrectName(String name) {
        try {
            return Pattern.compile(FullName.regexName).matcher(name).matches();
        } catch (NullPointerException e) {
            return false;
        }
    }
}