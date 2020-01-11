package com.cds.oppenheimer.dto.model;

import java.time.LocalDate;

import com.cds.oppenheimer.model.WorkingClassHero;

public class WorkingClassHeroMapper {

    public static WorkingClassHero toWorkingClassHero(WorkingClassHeroDTO workingClassHeroDto) {
        return new WorkingClassHero(
            workingClassHeroDto.getNatid(),
            workingClassHeroDto.getName(),
            workingClassHeroDto.getGender(),
            LocalDate.parse(workingClassHeroDto.getBirthday()),
            workingClassHeroDto.getSalary(),
            workingClassHeroDto.getTaxPaid()
        );
    }

    public static WorkingClassHeroDTO toWorkingClassHeroDTO(WorkingClassHero workingClassHero) {
        return new WorkingClassHeroDTO(
            workingClassHero.getNatid(),
            workingClassHero.getName(),
            workingClassHero.getGender(),
            workingClassHero.getBirthday().toString(),
            workingClassHero.getSalary(),
            workingClassHero.getTaxPaid()
        );
    }
}