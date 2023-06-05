package com.org.latelier.models.api.response;

public class TennisStats {
    float heightMedian;
    float averageIMC;
    CountryRatio countryRatio;

    public float getHeightMedian() {
        return heightMedian;
    }

    public void setHeightMedian(float heightMedian) {
        this.heightMedian = heightMedian;
    }

    public float getAverageIMC() {
        return averageIMC;
    }

    public void setAverageIMC(float averageIMC) {
        this.averageIMC = averageIMC;
    }

    public CountryRatio getCountryRatio() {
        return countryRatio;
    }

    public void setCountryRatio(CountryRatio countryRatio) {
        this.countryRatio = countryRatio;
    }
}
