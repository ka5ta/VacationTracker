package com.example.VTracker.errorAPI;

import lombok.Data;

@Data
public class NoSuchUserException extends ApiError {

    private String adUserID;

    public NoSuchUserException(String adUserID) {
        super();
        this.adUserID = adUserID;
        this.message = "No such user with ID: " + adUserID;

    }
}
