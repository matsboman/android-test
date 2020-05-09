package com.example.helloworld;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculateSumTest {

    @Test
    public void calculate() {
        int price = 1363;
        double sqm = 5.25;
        double minSqm = 11;
        int discount = 10;
        double combinedMeters = 4.6;
        int normTimeTotalCost = 5694;
        int additionalStaff = 1000;
        double establishment = 1.6;
        int quantity = 2;
        int hourlyRate = 500;

        CalculateSum calc = new
                CalculateSum(
                price,
                hourlyRate,
                quantity,
                sqm,
                minSqm,
                discount,
                combinedMeters,
                normTimeTotalCost,
                establishment,
                additionalStaff);

        assertEquals(29986, calc.getMaterialCost().intValue());
        assertEquals(-2999, calc.getRebateMaterial().intValue());
        assertEquals(26987, calc.getMaterialTotalCost().intValue());
        assertEquals(11388, calc.getNormTime().intValue());
        assertEquals(2000, calc.getWorkAdditional().intValue());
        assertEquals(1600, calc.getEstablishmentCost().intValue());
        assertEquals(14988, calc.getWorkTotalCost().intValue());
        assertEquals(41975, calc.getGrandTotal().intValue());
    }
}