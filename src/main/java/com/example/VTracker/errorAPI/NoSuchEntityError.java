package com.example.VTracker.errorAPI;

import lombok.Data;

import java.net.http.HttpRequest;

@Data
public class NoSuchEntityError {

    private String error = "No such object";
    private String message;
    private String reqPath;
    private String exceptionMessage;


    public NoSuchEntityError(String message,String reqPath, String exceptionMessage) {
        this.message=message;
        this.reqPath = reqPath;
        this.exceptionMessage = exceptionMessage;
    }
}
