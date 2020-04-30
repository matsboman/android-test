package com.example.helloworld;

import android.util.Log;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CalculateInstallation {
    private int combinedMeters;
    private double sqm;
    private int pricePerSqm;
    private double normTime;
    private double addition;
    private double establishment;
    private int numberOfMen;
    private double totalTime;
    private int hourlyRate;
    private int additionalMen;
    private int additionalHours;
    private int totalMaterial;
    private int minimumPrice;
    private int totalCostNormTime;
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

    CalculateInstallation(int price, int discount, int width, int height, int hourlyRate, int additionalMen, int additionalHours) {

        this.pricePerSqm = (int) Math.round(price * (1.0 - discount / 100.0));
        this.sqm = width * height / 1000000.0;
        this.totalMaterial = (int) Math.round(sqm * pricePerSqm);
        this.combinedMeters = (width + height) / 1000;
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
//        int totalCost = (int) Math.round(this.totalTime * this.hourlyRate);
        this.minimumPrice = (int) Math.round(this.normTime < MINIMUM_PRICE_LIMIT ?
                ((MINIMUM_PRICE_LIMIT + this.establishment) * this.hourlyRate) : (this.totalTime * this.hourlyRate));
        this.totalCostNormTime = Math.max(this.totalCost, this.minimumPrice);
    }

    public Integer getPricePerSqm() {
        return this.pricePerSqm;
    }

    public Double getSqm() {
        return this.sqm;
    }

    public Integer getHourlyRate() {
        return this.hourlyRate;
    }

    public Integer getCombinedMeters() {
        return this.combinedMeters;
    }

    public Double getNormTime() {
        return roundDouble(this.normTime);
    }

    public Double getEstablishment() {
        return roundDouble(this.establishment);
    }

    public Double getAddition() {
        return roundDouble(this.addition);
    }

    public Integer getNumberOfMen() {
        return this.numberOfMen;
    }

    public Double getTotalTime() {
        return roundDouble(this.totalTime);
    }

    public Integer getTotalPrice() {
        return (int) Math.ceil(this.hourlyRate * this.totalTime);
    }

    public Integer getMinimumPrice() {
        return this.minimumPrice;
    }

    public Integer getTotalLaborCost() {
        return Math.round(this.combinedMeters < COMBINED_METER_LIMIT_3 ?
                (this.getTotalCostNormTime() + this.getAdditionalStaffing()) : 0);
    }

    public Integer getAdditionalStaffing() {
        return this.hourlyRate * this.additionalMen * this.additionalHours;
    }

    public Integer getTotalMaterial() {
        return this.totalMaterial;
    }

    public Integer getTotalCostNormTime() {
        return this.totalCostNormTime;
    }

    private double roundDouble(double value) {
        return Math.round(value * 10) / 10.0;
    }
}
