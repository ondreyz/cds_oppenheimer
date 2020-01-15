package com.cds.oppenheimer.service;

import java.util.ArrayList;
import java.util.List;

import com.cds.oppenheimer.model.TaxRelief;
import com.cds.oppenheimer.model.WorkingClassHero;
import com.cds.oppenheimer.repository.WorkingClassHeroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaxReliefServiceImpl implements TaxReliefService {

    @Autowired
    private WorkingClassHeroRepository workingClassHeroRepository;

    public List<TaxRelief> getTaxReliefsForAllWorkingClassHeroes() {
        List<WorkingClassHero> allWorkingClassHeroes = workingClassHeroRepository.findAll();
        List<TaxRelief> taxReliefs = new ArrayList<>(allWorkingClassHeroes.size());
        for (WorkingClassHero workingClassHero : allWorkingClassHeroes) {
            taxReliefs.add(new TaxRelief(workingClassHero));
        }
        return taxReliefs;
    }
}