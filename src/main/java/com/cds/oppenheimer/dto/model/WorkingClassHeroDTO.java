package com.cds.oppenheimer.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencsv.bean.CsvBindByName;

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
    @CsvBindByName(column="natid", required=true)
    private String natid;

    @CsvBindByName(column="name", required=true)
    private String name;

    @CsvBindByName(column="gender", required=true)
    private String gender;

    @CsvBindByName(column="birthday", required=true)
    private String birthday;

    @CsvBindByName(column="salary", required=true)
    private double salary;

    @CsvBindByName(column="tax", required=true)
    private double taxPaid;
}