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
        int normTimeTotalCost = 6494;
        int additionalStaff = 1000;

        CalculateSum calc = new CalculateSum(price, sqm, minSqm, discount, combinedMeters, normTimeTotalCost, additionalStaff);

        assertEquals(14993, calc.getMaterialCost().intValue());
        assertEquals(13494, calc.getMaterialTotalCost().intValue());
        assertEquals(-1499, calc.getRebateMaterial().intValue());
        assertEquals(6494, calc.getNormTime().intValue());
        assertEquals(1000, calc.getWorkAdditional().intValue());
        assertEquals(7494, calc.getWorkTotalCost().intValue());
        assertEquals(20988, calc.getGrandTotal().intValue());
    }
}