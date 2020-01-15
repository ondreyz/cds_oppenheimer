package com.cds.oppenheimer.model;

import java.text.DecimalFormat;

import com.cds.oppenheimer.enums.Gender;

import lombok.Getter;

@Getter
public class TaxRelief {
    private static final double MINIMUM_TAX_RELIEF_AMOUNT = 50.00;
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    private WorkingClassHero workingClassHero;
    private double taxReliefAmount;

    public TaxRelief (WorkingClassHero workingClassHero) {
        this.workingClassHero = workingClassHero;
        this.taxReliefAmount = computeTaxReliefAmount(
            workingClassHero.getSalary(),
            workingClassHero.getTaxPaid(),
            getVariable(workingClassHero),
            getGenderBonus(workingClassHero));
    }

    private double computeTaxReliefAmount(double salary, double taxPaid, double variable, double genderBonus) {
        double result = ((salary - taxPaid) * variable) + genderBonus;
        
        String roundedResultString = decimalFormat.format(result);
        double roundedResult = Double.parseDouble(roundedResultString);

        return roundedResult > 0 && roundedResult < MINIMUM_TAX_RELIEF_AMOUNT
            ? MINIMUM_TAX_RELIEF_AMOUNT
            : roundedResult;
    }

    private double getVariable(WorkingClassHero workingClassHero) {
        int age = workingClassHero.getAge();

        if (age <= 18) {
            return 1; // at most 18
        } else if (age <= 35) {
            return 0.8; // at most 35 
        } else if (age <= 50) {
            return 0.5; // at most 50
        } else if (age <= 75) {
            return 0.367; // at most 75
        } else {
            return 0.05; // at least 76
        }
    }

    private double getGenderBonus(WorkingClassHero workingClassHero) {
        Gender gender = workingClassHero.getGender();
        switch (gender) {
            case MALE:
                return 0;
            case FEMALE:
                return 500;
            default:
                return 0;
        }
    }
}