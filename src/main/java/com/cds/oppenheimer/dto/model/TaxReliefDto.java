package com.cds.oppenheimer.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaxReliefDto {
    private String natid;
    private double taxReliefAmount;
    private String name;
}