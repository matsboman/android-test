package com.example.helloworld;

public class CalculateTime {
    private double combinedMeters;
    private double denominator;
    prviate double constant;

    CalculateTime(double combinedMeters) {
        this.combinedMeters = combinedMeters;
        this.denominator =
                2.6 + this.combinedMeters > 3.2 ? -1.5 : 0 + this.combinedMeters > 5.5 ? 0.15 : 0;

    }

}
