package com.example.helloworld;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculateInstallationTest {

    @Test
    public void calculateMaterial() {
        int price = 1363;
        int discount = 10;
        int width = 2500;
        int height = 2100;
        int hourlyRate = 500;
        double minSqm = 11;
        int additionalMen = 1;
        int additionalHours = 2;

        CalculateInstallation calc = new
                CalculateInstallation(price, discount, width, height, hourlyRate, minSqm, additionalMen, additionalHours);

        assertEquals(5.25, calc.getSqm(), 0.001);
        assertEquals(1226.7, calc.getPricePerSqm(), 0.001);
        assertEquals(6440, calc.getMaterialPrice().intValue());
        assertEquals(13494, calc.getMaterialMinPrice().intValue());
        assertEquals(13494, calc.getTotalMaterial().intValue());
    }

    @Test
    public void calculateLabor() {
        int price = 1363;
        int discount = 10;
        int width = 2500;
        int height = 2100;
        int hourlyRate = 500;
        double minSqm = 11;
        int additionalMen = 1;
        int additionalHours = 2;

        CalculateInstallation calc = new
                CalculateInstallation(price, discount, width, height, hourlyRate, minSqm, additionalMen, additionalHours);

        assertEquals(4.6, calc.getCombinedMeters(), 0.001);
        assertEquals(11.39, calc.getNormTime(), 0.001);
        assertEquals(1.6, calc.getEstablishment(), 0.001);
        assertEquals(0, calc.getAddition(), 0.001);
        assertEquals(12.99, calc.getTotalTime(), 0.001);
        assertEquals(500, calc.getHourlyRate().intValue());
        assertEquals(6494, calc.getTotalPrice().intValue());
        assertEquals(6494, calc.getTotalCostNormTime().intValue());
        assertEquals(1000, calc.getAdditionalStaffing().intValue());
        assertEquals(7494, calc.getTotalLaborCost().intValue());
    }
}