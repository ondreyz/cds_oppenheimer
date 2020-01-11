package com.cds.oppenheimer.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cds.oppenheimer.model.WorkingClassHero;

@Repository
public interface WorkingClassHeroRepository extends JpaRepository<WorkingClassHero, String> {
}
