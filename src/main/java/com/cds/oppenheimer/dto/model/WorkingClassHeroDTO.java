package com.cds.oppenheimer.dto.model;

import com.cds.oppenheimer.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkingClassHeroDTO {
    private String natid;
    private String name;
    private Gender gender;
    private String birthday;
    private long salary;
    private long taxPaid;
}