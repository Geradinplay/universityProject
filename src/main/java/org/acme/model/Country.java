package org.acme.model;

import java.util.List;

public class Country {
    private String name;
    private String countryCode;


    public Country(String countryCode, String name) {
        this.name=name;
        this.countryCode = countryCode;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
