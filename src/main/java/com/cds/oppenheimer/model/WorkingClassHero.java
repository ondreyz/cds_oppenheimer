package com.cds.oppenheimer.model;

import java.time.LocalDate;
import java.time.Period;

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
    private static final String MASK_NATID_CHARACTER = "$";
    private static final int MASK_NATID_START_INDEX = 4; // 5th character onwards

    @Id
    private String natid;
    private String name;
    private Gender gender;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;

    private double salary;
    private double taxPaid;

    /**
     * Computes current age based on birthday
     * @return Age in years (rounded up, minimum age 0)
     */
    public int getAge() {
        Period period = Period.between(this.birthday, LocalDate.now());
        int age = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        // Compensate for part-thereof of a year
        if (months > 0 || days > 0) {
            age += 1;
        } else if (months < 0 || days < 0) {
            age -= 1;
        }
        return age < 0
            ? 0 // Set age to zero if value is negative
            : age;
    }

    public String getMaskedNatid() {
        int natidLength = natid.length();
        int numberOfCharactersToMask = natidLength - MASK_NATID_START_INDEX;
        if (numberOfCharactersToMask < 1) {
            return natid;
        } else {
            StringBuilder sb = new StringBuilder(numberOfCharactersToMask);
            for (int i = 0; i < numberOfCharactersToMask; i++) {
                sb.append(MASK_NATID_CHARACTER);
            }
            return natid.substring(0, MASK_NATID_START_INDEX) + sb.toString();
        }
    }
}
