package com.cds.oppenheimer.dto.model;

import java.time.LocalDate;

import com.cds.oppenheimer.enums.Gender;
import com.cds.oppenheimer.model.WorkingClassHero;

public class WorkingClassHeroMapper {

    public static WorkingClassHero toWorkingClassHero(WorkingClassHeroDTO workingClassHeroDto) {
        return new WorkingClassHero(
            workingClassHeroDto.getNatid(),
            workingClassHeroDto.getName(),
            Gender.get(workingClassHeroDto.getGender()),
            LocalDate.parse(workingClassHeroDto.getBirthday()),
            workingClassHeroDto.getSalary(),
            workingClassHeroDto.getTaxPaid()
        );
    }

    public static WorkingClassHeroDTO toWorkingClassHeroDTO(WorkingClassHero workingClassHero) {
        return new WorkingClassHeroDTO(
            workingClassHero.getNatid(),
            workingClassHero.getName(),
            workingClassHero.getGender().getGender(), // get string value of gender
            workingClassHero.getBirthday().toString(),
            workingClassHero.getSalary(),
            workingClassHero.getTaxPaid()
        );
    }
}