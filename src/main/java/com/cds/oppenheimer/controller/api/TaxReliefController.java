package com.cds.oppenheimer.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import com.cds.oppenheimer.dto.model.TaxReliefDto;
import com.cds.oppenheimer.dto.model.TaxReliefMapper;
import com.cds.oppenheimer.dto.response.ApiResponse;
import com.cds.oppenheimer.model.TaxRelief;
import com.cds.oppenheimer.service.TaxReliefService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TaxReliefController {
    @Autowired
    private TaxReliefService taxReliefService;

    @GetMapping("/taxrelief/workingclassheroes")
    public ResponseEntity<ApiResponse<Object>> getTaxReliefsForAllWorkingClassHeroes() {
        List<TaxRelief> taxReliefs = taxReliefService.getTaxReliefsForAllWorkingClassHeroes();
        List<TaxReliefDto> taxReliefDtos = taxReliefs
            .stream()
            .map(taxRelief -> TaxReliefMapper.toTaxReliefDto(taxRelief))
            .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<Object>("success", "", taxReliefDtos));
    }
}