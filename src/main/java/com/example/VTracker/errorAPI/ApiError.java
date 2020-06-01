package com.example.VTracker.errorAPI;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {

    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    private ApiError(){
        timestamp = LocalDateTime.now();
    }

    ApiError(HttpStatus status){
        this();
        this.status = status;
    }

    ApiError(HttpStatus status, Throwable ex) {
        this(status);
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }
}
