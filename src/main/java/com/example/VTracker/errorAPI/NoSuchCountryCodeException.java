package com.example.VTracker.errorAPI;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;

@Data
public class NoSuchCountryCodeException extends ApiError {


    private String countryCode;

    public NoSuchCountryCodeException(String countryCode) {
        super();
        this.message = "There is no such Country code as: "+countryCode;
        this.countryCode = countryCode;
    }
}
