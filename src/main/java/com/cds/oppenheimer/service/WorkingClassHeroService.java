package com.cds.oppenheimer.service;

import java.util.List;

import com.cds.oppenheimer.exception.DuplicateEntityException;
import com.cds.oppenheimer.model.WorkingClassHero;

public interface WorkingClassHeroService {
    WorkingClassHero addWorkingClassHero(WorkingClassHero workingClassHero) throws DuplicateEntityException;
    List<WorkingClassHero> addWorkingClassHeroes(List<WorkingClassHero> workingClassHero) throws DuplicateEntityException;
}