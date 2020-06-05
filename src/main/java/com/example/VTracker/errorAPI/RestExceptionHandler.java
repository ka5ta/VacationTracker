package com.example.VTracker.errorAPI;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public NoSuchEntityError handleNotFoundResult(HttpServletRequest req, EmptyResultDataAccessException exception){
        return new NoSuchEntityError();
    //todo NoSuchEntityError class more useful
    }

}