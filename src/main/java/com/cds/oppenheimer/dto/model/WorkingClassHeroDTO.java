package com.cds.oppenheimer.dto.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.cds.oppenheimer.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

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
    // @CsvCustomBindByName(column="gender")
    private String gender;

    @CsvBindByName(column="birthday", required=true)
    private String birthday;

    @CsvBindByName(column="salary", required=true)
    private long salary;

    @CsvBindByName(column="tax", required=true)
    private long taxPaid;
}