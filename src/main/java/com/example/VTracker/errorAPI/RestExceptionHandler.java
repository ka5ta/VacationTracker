package com.example.VTracker.errorAPI;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Properties;
import java.util.Set;


@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RestExceptionHandler {

    // -- Handles exception from HttpRequest for deleting or getting not existing entity --//
    @ExceptionHandler(EmptyResultDataAccessException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiError handleNotFoundResult(HttpServletRequest req, EmptyResultDataAccessException exception){
        String message = "No such element in database";
        return new ApiError(message, req,exception);
    }

    // -- Handle duplicated values addition --//
    @ExceptionHandler(DataIntegrityViolationException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public Properties handleDuplicatedValue(HttpServletRequest req, DataIntegrityViolationException exception){
        String className = exception.getStackTrace().getClass().getName();
        String rootCause = exception.getRootCause().getMessage();

        Properties properties = new Properties();
        properties.setProperty("error", rootCause);
        properties.setProperty("class",className);
        return properties;
    }

    // -- Handle 'NoSuchCountryCodeException' adding country code which doesn't exist in ISO Country list --//
    @ExceptionHandler(HttpMessageNotReadableException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMissingCountryCode(HttpServletRequest req, HttpMessageNotReadableException exception){
        Throwable rootCause = exception.getRootCause();
        if (rootCause instanceof NoSuchCountryCodeException) {
            return (NoSuchCountryCodeException) rootCause;
        }
        throw exception;
    }

    // -- Handles 'ConstraintViolationException' that comes through TransactionSystemException -- //
    // -- returns root cause error -- //
    @ExceptionHandler(TransactionSystemException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected EntityValidationError handleConstraintValidation(HttpServletRequest req, TransactionSystemException exception){

        ConstraintViolationException rootCause =(ConstraintViolationException)exception.getRootCause();
        return new EntityValidationError(rootCause);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public EntityValidationError handleConstraintViolationError(HttpServletRequest req, ConstraintViolationException ex ){
        return new EntityValidationError(ex);
    }

    @ExceptionHandler(NoSuchUserException.class)
    public ApiError handleNoSuchUserError(HttpServletRequest req, NoSuchUserException ex){
        return ex;
    }

    @ExceptionHandler(NoSuchCountryCodeException.class)
    public NoSuchCountryCodeException handleNoSuchCountryError(HttpServletRequest req, NoSuchCountryCodeException ex){
        return ex;
    }

}