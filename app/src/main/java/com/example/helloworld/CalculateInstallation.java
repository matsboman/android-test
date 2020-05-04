package com.example.helloworld;

import android.util.Log;

import java.math.RoundingMode;
import java.text.DecimalFormat;

class CalculateInstallation {
    private double combinedMeters;
    private double sqm;
    private double pricePerSqm;
    private int materialPrice;
    private int materialMinPrice;
    private double normTime;
    private double addition;
    private double establishment;
    private int numberOfMen;
    private double totalTime;
    private int totalPrice;
    private int hourlyRate;
    private int additionalMen;
    private int additionalHours;
    private int totalMaterial;
    private int minimumPrice;
    private int totalCostNormTime;
    private int totalLaborCost;
    private static final double COMBINED_METER_LIMIT_1 = 3.2;
    private static final double COMBINED_METER_LIMIT_2 = 5.5;
    private static final double COMBINED_METER_LIMIT_3 = 8.6;
    private static final double COMBINED_METER_DENOMINATOR_CONST_1 = 2.6;
    private static final double COMBINED_METER_DENOMINATOR_CONST_2 = -1.5;
    private static final double COMBINED_METER_DENOMINATOR_CONST_3 = 0.15;
    private static final double COMBINED_METER_CONST_1 = 0.45;
    private static final double COMBINED_METER_CONST_2 = -6.55;
    private static final double COMBINED_METER_CONST_3 = 5.5;
    private static final double ADDITION_COMBINED_METER_LIMIT_1 = 1.9;
    private static final double ADDITION_COMBINED_METER_LIMIT_2 = 2.6;
    private static final double ADDITION_COMBINED_METER_LIMIT_3 = 3.2;
    private static final double ADDITION_CONST_1 = 0.5;
    private static final double ADDITION_CONST_2 = 0.3;
    private static final double ADDITION_CONST_3 = -0.8;
    private static final double ESTABLISHMENT_COMBINED_METER_LIMIT_1 = 1.9;
    private static final double ESTABLISHMENT_COMBINED_METER_LIMIT_2 = 9.9;
    private static final double ESTABLISHMENT_CONST_1 = 0.8;
    private static final double ESTABLISHMENT_CONST_2 = 0.7;
    private static final double ESTABLISHMENT_NUM_MEN_LIMIT_1 = 1.3;
    private static final double ESTABLISHMENT_NUM_MEN_LIMIT_2 = 2.0;
    private static final double ESTABLISHMENT_NUM_MEN_LIMIT_3 = 2.7;
    private static final double MINIMUM_PRICE_LIMIT = 0.7;
    private static final int NUM_MEN_CONST = 1;

    CalculateInstallation(int price, int discount, int width, int height, int hourlyRate, double minSqm, int additionalMen, int additionalHours) {
        calculateMaterial(price, discount, width, height, minSqm);
        calculateLabor(hourlyRate, additionalMen, additionalHours);
    }

    void calculateMaterial(int price, int discount, int width, int height, double minSqm) {
        double discountFactor = discount / 100.0;
        this.sqm = width * height / 1000000.0;
        this.pricePerSqm = price * (1.0 - discountFactor);
        this.materialPrice = (int) Math.round(pricePerSqm * sqm);
        this.materialMinPrice = (int) Math.round(Math.max(minSqm, this.sqm) * this.pricePerSqm);
        this.totalMaterial = Math.max(this.materialMinPrice, this.materialPrice);
        this.combinedMeters = (width + height) / 1000.0;
    }

    void calculateLabor(int hourlyRate, int additionalMen, int additionalHours) {
        this.hourlyRate = hourlyRate;
        this.additionalMen = additionalMen;
        this.additionalHours = additionalHours;
        double denominator =
                COMBINED_METER_DENOMINATOR_CONST_1 +
                        (COMBINED_METER_LIMIT_1 < this.combinedMeters ? COMBINED_METER_DENOMINATOR_CONST_2 : 0) +
                        (COMBINED_METER_LIMIT_2 < this.combinedMeters ? COMBINED_METER_DENOMINATOR_CONST_3 : 0);
        double constant = COMBINED_METER_CONST_1 +
                (COMBINED_METER_LIMIT_1 <= this.combinedMeters ? COMBINED_METER_CONST_2 : 0) +
                (COMBINED_METER_LIMIT_2 <= this.combinedMeters ? COMBINED_METER_CONST_3 : 0);
        this.addition = (ADDITION_COMBINED_METER_LIMIT_1 < this.combinedMeters ? ADDITION_CONST_1 : 0) +
                (ADDITION_COMBINED_METER_LIMIT_2 < this.combinedMeters ? ADDITION_CONST_2 : 0) +
                (ADDITION_COMBINED_METER_LIMIT_3 < this.combinedMeters ? ADDITION_CONST_3 : 0);
        this.establishment = ESTABLISHMENT_CONST_1 +
                (ESTABLISHMENT_COMBINED_METER_LIMIT_1 < this.combinedMeters ? ESTABLISHMENT_CONST_1 : 0) +
                (ESTABLISHMENT_COMBINED_METER_LIMIT_2 < this.combinedMeters ? ESTABLISHMENT_CONST_2 : 0);
        this.normTime = Math.pow(this.combinedMeters / denominator, 2d) + constant;
        this.numberOfMen = NUM_MEN_CONST +
                (ESTABLISHMENT_NUM_MEN_LIMIT_1 < this.combinedMeters ? NUM_MEN_CONST : 0) +
                (ESTABLISHMENT_NUM_MEN_LIMIT_2 < this.combinedMeters ? NUM_MEN_CONST : 0) +
                (ESTABLISHMENT_NUM_MEN_LIMIT_3 < this.combinedMeters ? NUM_MEN_CONST : 0);
        this.totalTime = this.normTime + this.establishment + this.addition;
        this.totalPrice = (int) Math.round(this.totalTime * this.hourlyRate);
        this.minimumPrice = (int) Math.round(this.normTime < MINIMUM_PRICE_LIMIT ?
                ((MINIMUM_PRICE_LIMIT + this.establishment) * this.hourlyRate) : (this.totalTime * this.hourlyRate));
        this.totalCostNormTime = Math.max(this.totalPrice, this.minimumPrice);
        this.totalLaborCost = Math.round(this.combinedMeters < COMBINED_METER_LIMIT_3 ?
                (this.totalCostNormTime + this.hourlyRate * this.additionalMen * this.additionalHours) : 0);
    }

    Double getPricePerSqm() {
        return roundDouble(this.pricePerSqm);
    }

    Double getSqm() {
        return this.sqm;
    }

    Integer getHourlyRate() {
        return this.hourlyRate;
    }

    Double getCombinedMeters() {
        return this.combinedMeters;
    }

    Double getNormTime() {
        return roundDouble(this.normTime);
    }

    Double getEstablishment() {
        return roundDouble(this.establishment);
    }

    Double getAddition() {
        return roundDouble(this.addition);
    }

    Integer getNumberOfMen() {
        return this.numberOfMen;
    }

    Double getTotalTime() {
        return roundDouble(this.totalTime);
    }

    Integer getTotalPrice() {
        return this.totalPrice;
    }

    Integer getMinimumPrice() {
        return this.minimumPrice;
    }

    Integer getTotalLaborCost() {
        return this.totalLaborCost;
    }

    Integer getAdditionalStaffing() {
        return this.hourlyRate * this.additionalMen * this.additionalHours;
    }

    Integer getTotalCostNormTime() {
        return this.totalCostNormTime;
    }

    Integer getMaterialPrice() {
        return this.materialPrice;
    }
    Integer getMaterialMinPrice() {
        return this.materialMinPrice;
    }
    Integer getTotalMaterial() {
        return this.totalMaterial;
    }

    private double roundDouble(double value) {
        return Math.round(value * 100) / 100.0;
    }
}
