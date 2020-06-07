package com.example.VTracker.errorAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties({"cause","stackTrace","localizedMessage","suppressed"})
public class ApiError extends Throwable {

    private LocalDateTime timestamp;
    protected String message;
    private String reqPath;
    private String debugMessage;


    protected ApiError(){
        super();
        timestamp = LocalDateTime.now();
    }

    ApiError(String message, HttpServletRequest req) {
        this();
        this.message = message;
        this.reqPath = req.getServletPath();
    }

    ApiError(String message, HttpServletRequest req,  Exception ex) {
        this();
        this.message = message;
        this.reqPath = req.getServletPath();
        this.debugMessage = ex.getLocalizedMessage();
    }

}
