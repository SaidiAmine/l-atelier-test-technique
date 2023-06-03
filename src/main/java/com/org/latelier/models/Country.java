package com.org.latelier.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

@Embeddable
public class Country {
    @JsonProperty("picture")
    String countryPicture;
    String code;

    public String getCountryPicture() {
        return countryPicture;
    }

    public void setCountryPicture(String countryPicture) {
        this.countryPicture = countryPicture;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
