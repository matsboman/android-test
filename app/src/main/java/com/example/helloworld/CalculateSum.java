package com.example.helloworld;

class CalculateSum {
    private int materialCost;
    private int rebateMaterial;
    private int normTime;
    private int workAdditional;
    private static final double COMBINED_METER_LIMIT = 8.6;

    CalculateSum(int price, double sqm, double minSqm, int discount, int combinedMeters, int normTimeTotalCost, int additionalStaff) {
        this.materialCost = (int) Math.round(Math.max(sqm, minSqm) * price);
        this.rebateMaterial = (int) -Math.round((Math.max(minSqm, sqm) * price * discount / 100.0));
        this.normTime = combinedMeters < COMBINED_METER_LIMIT ? normTimeTotalCost : 0;
        this.workAdditional = combinedMeters < COMBINED_METER_LIMIT ? additionalStaff : 0;
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

    Integer getWorkTotalCost() {
        return this.normTime + this.workAdditional;
    }

    Integer getGrandTotal() {
        return this.getWorkTotalCost() > 0 ? this.getMaterialTotalCost() + this.getWorkTotalCost() : 0;
    }
}
