package com.cds.oppenheimer.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("M"),
    FEMALE("F");

    private String gender;

    // Reverse-lookup map for getting a day from an abbreviation
    private static final Map<String, Gender> lookup = new HashMap<String, Gender>();
    static {
        for (Gender g : Gender.values()) {
            lookup.put(g.getGender(), g);
        }
    }

    private Gender(String gender) {
        this.gender = gender;
    }

    @JsonValue
    public String getGender() {
        return this.gender;
    }

    public static Gender get(String genderValue) {
        return lookup.get(genderValue);
    }
}