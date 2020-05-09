package com.example.helloworld;

class CalculateSum {
    private int materialCost;
    private int hourlyRate;
    private int rebateMaterial;
    private int normTime;
    private int workAdditional;
    private int establishmentCost;
    private static final double COMBINED_METER_LIMIT = 8.6;

    CalculateSum(int price, int hourlyRate, int quantity, double sqm, double minSqm, int discount, double combinedMeters, int normTimeTotalCost, double establishment, int additionalStaff) {
        calculate(price, hourlyRate, quantity, sqm, minSqm, discount, combinedMeters, normTimeTotalCost, establishment, additionalStaff);
    }

    void calculate(int price, int hourlyRate, int quantity, double sqm, double minSqm, int discount, double combinedMeters, int normTimeTotalCost, double establishment, int additionalStaff) {
        this.hourlyRate = hourlyRate;
        this.establishmentCost = (int) Math.round(establishment * hourlyRate * quantity);
        this.materialCost = (int) Math.round(Math.max(sqm, minSqm) * price * quantity);
        this.rebateMaterial = (int) -Math.round((Math.max(minSqm, sqm) * price * discount * quantity / 100.0));
        this.normTime = quantity * (combinedMeters < COMBINED_METER_LIMIT ? normTimeTotalCost : 0);
        this.workAdditional = quantity * (combinedMeters < COMBINED_METER_LIMIT ? additionalStaff : 0);
    }

    Integer getMaterialCost() {
        return this.materialCost;
    }

    Integer getRebateMaterial() {
        return this.rebateMaterial;
    }

    Integer getMaterialTotalCost() {
        return this.materialCost + this.rebateMaterial;
    }

    Integer getNormTime() {
        return this.normTime;
    }

    Integer getWorkAdditional() {
        return this.workAdditional;
    }

    Integer getEstablishmentCost() {
        return this.establishmentCost;
    }

    Integer getWorkTotalCost() {
        return this.normTime + this.workAdditional + this.establishmentCost;
    }

    Integer getGrandTotal() {
        return this.getWorkTotalCost() > 0 ? this.getMaterialTotalCost() + this.getWorkTotalCost() : 0;
    }
}
