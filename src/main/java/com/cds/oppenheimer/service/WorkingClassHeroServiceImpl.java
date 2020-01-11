package com.cds.oppenheimer.service;

import java.util.Optional;

import com.cds.oppenheimer.exception.DuplicateEntityException;
import com.cds.oppenheimer.model.WorkingClassHero;
import com.cds.oppenheimer.repository.WorkingClassHeroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkingClassHeroServiceImpl implements WorkingClassHeroService {

    @Autowired
    private WorkingClassHeroRepository workingClassHeroRepository;

    public WorkingClassHero addWorkingClassHero(WorkingClassHero workingClassHeroToAdd) throws DuplicateEntityException {
        Optional<WorkingClassHero> workingClassHero = workingClassHeroRepository.findById(workingClassHeroToAdd.getNatid());
        if (!workingClassHero.isPresent()) {
            workingClassHeroRepository.save(workingClassHeroToAdd);
            return workingClassHeroToAdd;
        }
        throw new DuplicateEntityException("WorkingClassHero `" + workingClassHeroToAdd.getNatid() + "` already exists.");
    }
}