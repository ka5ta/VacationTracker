package com.example.VTracker.errorAPI;

import lombok.Data;

@Data
public class NoSuchUserException extends ApiError{

    private String adUserID;

    public NoSuchUserException(String adUserID) {
        super();
        this.message="No such user with ID: "+adUserID;

    }
}
