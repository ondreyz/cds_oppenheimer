package com.cds.oppenheimer.service;

import com.cds.oppenheimer.exception.DuplicateEntityException;
import com.cds.oppenheimer.model.WorkingClassHero;

public interface WorkingClassHeroService {
    WorkingClassHero addWorkingClassHero(WorkingClassHero workingClassHero) throws DuplicateEntityException;
}