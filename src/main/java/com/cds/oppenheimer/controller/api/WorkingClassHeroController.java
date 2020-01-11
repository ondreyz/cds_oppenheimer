package com.cds.oppenheimer.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.cds.oppenheimer.dto.model.WorkingClassHeroDTO;
import com.cds.oppenheimer.dto.model.WorkingClassHeroMapper;
import com.cds.oppenheimer.dto.response.ApiResponse;
import com.cds.oppenheimer.exception.DuplicateEntityException;
import com.cds.oppenheimer.model.WorkingClassHero;
import com.cds.oppenheimer.service.WorkingClassHeroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WorkingClassHeroController {
    @Autowired
    private WorkingClassHeroService workingClassHeroService;

    /**
     * Handles incoming POST API "/api/workingclasshero"
     * @param workingClassHeroToAdd
     * @return
     */
    @PostMapping("/workingclasshero")
    public ResponseEntity<ApiResponse<Object>> addWorkingClassHeroRecord(@RequestBody @Valid WorkingClassHeroDTO workingClassHeroToAdd) {
        WorkingClassHero workingClassHeroModel = WorkingClassHeroMapper.toWorkingClassHero(workingClassHeroToAdd);
        try {
            workingClassHeroService.addWorkingClassHero(workingClassHeroModel);
            return ResponseEntity.ok(new ApiResponse<Object>("success", "", WorkingClassHeroMapper.toWorkingClassHeroDTO(workingClassHeroModel)));
        } catch (DuplicateEntityException conflictException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<Object>("error", conflictException.getMessage(), null));
        }
    }

    /**
     * Handles incoming POST API "/api/workingclassheroes"
     * @param workingClassHeroesToAdd
     * @return
     */
    @PostMapping("/workingclassheroes")
    public ResponseEntity<ApiResponse<Object>> addWorkingClassHeroRecords(@RequestBody @Valid List<WorkingClassHeroDTO> workingClassHeroesToAdd) {
        List<WorkingClassHero> workingClassHeroes =
            workingClassHeroesToAdd
                .stream()
                .map(workingClassHeroToAdd -> WorkingClassHeroMapper.toWorkingClassHero(workingClassHeroToAdd))
                .collect(Collectors.toList());
        try {
            workingClassHeroService.addWorkingClassHeroes(workingClassHeroes);
            return ResponseEntity.ok(new ApiResponse<Object>("success", "", workingClassHeroesToAdd));
        } catch (DuplicateEntityException conflictException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<Object>("error", conflictException.getMessage(), null));
        }
    }
}