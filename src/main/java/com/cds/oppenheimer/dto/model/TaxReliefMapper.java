package com.cds.oppenheimer.dto.model;

import com.cds.oppenheimer.model.TaxRelief;

public class TaxReliefMapper {

    public static TaxReliefDto toTaxReliefDto(TaxRelief taxRelief) {
        return new TaxReliefDto(
            taxRelief.getWorkingClassHero().getMaskedNatid(),
            taxRelief.getTaxReliefAmount(),
            taxRelief.getWorkingClassHero().getName());
    }
}