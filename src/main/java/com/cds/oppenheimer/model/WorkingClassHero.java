package com.cds.oppenheimer.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.cds.oppenheimer.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WorkingClassHero {
    @Id
    private String natid;
    private String name;
    private Gender gender;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;

    private long salary;
    private long taxPaid;
}
