package com.cds.oppenheimer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<WorkingClassHero> addWorkingClassHeroes(List<WorkingClassHero> workingClassHeroesToAdd) throws DuplicateEntityException {
        List<String> workingClassHeroesToAddIdList =
            workingClassHeroesToAdd
                .stream()
                .map(workingClassHero -> workingClassHero.getNatid())
                .collect(Collectors.toList());
        List<WorkingClassHero> workingClassHeroes = workingClassHeroRepository.findAllById(workingClassHeroesToAddIdList);
        if (workingClassHeroes.isEmpty()) {
            workingClassHeroRepository.saveAll(workingClassHeroesToAdd);
            return workingClassHeroesToAdd;
        }
        // Duplicates exist, throw error with duplicate ids
        String duplicateIdString =
            workingClassHeroes
                .stream()
                .map(workingClassHero -> "`" + workingClassHero.getNatid() + "`")
                .collect(Collectors.joining(", "));
        throw new DuplicateEntityException("WorkingClassHeroes " + duplicateIdString + " already exists.");
    }
}